package ru.spbu.apcyb.svp.tasks.third;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.third.responses.DiskInfoResponse;
import ru.spbu.apcyb.svp.tasks.third.responses.UploadLinkResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class YandexDiskClientTest {
    private HttpClient httpClient;
    private HttpResponse<String> httpResponse;
    private YandexDiskClient yandexDiskClient;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        // Creating mock objects
        httpClient = mock(HttpClient.class);
        httpResponse = mock(HttpResponse.class);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        yandexDiskClient = new YandexDiskClient("token", httpClient);
    }

    @Test
    void getFakeInfoSuccess() throws Exception {
        // Mock successful response from server
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("{ \"total_space\": 1000, \"used_space\": 500, \"trash_size\": 100 }");

        // Execute the request
        DiskInfoResponse response = yandexDiskClient.getDiskInfo();

        // Checking that the request is successful
        assertTrue(response.isSuccess());
        assertEquals(1000, response.getTotalSpace());
        assertEquals(500, response.getUsedSpace());
        assertEquals(100, response.getTrashSize());
    }

    @Test
    void getFakeInfoError() throws Exception {
        // Mock unsuccessful response from server
        when(httpResponse.statusCode()).thenReturn(401);
        when(httpResponse.body()).thenReturn("Unauthorized");

        // Execute the request
        DiskInfoResponse response = yandexDiskClient.getDiskInfo();

        // Checking that the request was not successful
        assertFalse(response.isSuccess());
        assertEquals("Error: 401 - Unauthorized", response.getErrorMessage());
    }

    @Test
    void testFakeGetUploadLink() throws Exception {
        // Mock responses
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("{ \"href\": \"https://example.com/upload\", \"method\": " + "\"PUT\" }");

        // Get the download link
        UploadLinkResponse response = yandexDiskClient.getUploadLink("/disk/test.txt");

        // Checking the result
        assertTrue(response.isSuccess());
        assertEquals("https://example.com/upload", response.getHref());
        assertEquals("PUT", response.getMethod());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFakeUploadFile() throws Exception {
        // Mock responses
        when(httpResponse.statusCode()).thenReturn(201);

        // Temp test file
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "Temporary file for testing");

        // Upload file
        yandexDiskClient.uploadFile("https://example.com/upload", tempFile);

        // Checking that only 1 PUT request was called
        verify(httpClient, times(1)).send(argThat(
                request -> request.uri().toString().equals("https://example.com/upload") && request.method().equals("PUT")
        ), any(HttpResponse.BodyHandler.class));

        Files.deleteIfExists(tempFile);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFakeUploadFileToDisk() throws Exception {
        // Mock for getting upload URL
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("{ \"href\": \"https://example.com/upload\", \"method\": \"PUT\" }");

        // Mock for file upload response
        HttpResponse<String> mockUploadResponse = mock(HttpResponse.class);
        when(mockUploadResponse.statusCode()).thenReturn(201);

        when(httpClient.send(argThat(
                request -> request.uri().toString().startsWith("https://example.com/upload")
        ), any(HttpResponse.BodyHandler.class))).thenReturn(mockUploadResponse);

        // Temp test file creation
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "Temporary file for testing");

        // Upload the file to Yandex.Disk
        yandexDiskClient.uploadFileToDisk("/disk/test.txt", tempFile);

        // Verifying that a request for the upload URL was called
        verify(httpClient, times(1)).send(argThat(
                request -> request.uri().toString().contains("/v1/disk/resources/upload") && request.method().equals("GET")
        ), any(HttpResponse.BodyHandler.class));

        // Verifying that a request was made to upload the file
        verify(httpClient, times(1)).send(argThat(
                request -> request.uri().toString().equals("https://example.com/upload") && request.method().equals("PUT")
        ), any(HttpResponse.BodyHandler.class));

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testFakeDeleteFiles() throws IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(204);

        boolean result = yandexDiskClient.deletePath("file/exists.txt", true);

        assertTrue(result);
        //noinspection unchecked
        verify(httpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    /**
     * This test will upload and move to the trash test{}.txt file with "Temporary file for testing" content.
     */
    @Test
    void testReal() throws IOException, InterruptedException {
        String token = "insert-your-token";

        var client = new YandexDiskClient(token);

        Path tempFile = Files.createTempFile("test", ".txt");
        try {
            Files.writeString(tempFile, "Temporary file for testing");

            assertDoesNotThrow(() -> client.uploadFileToDisk("test.txt", tempFile));

            assertTrue(client.movePath("test.txt", "moved.txt", true));
            assertTrue(client.copyPath("moved.txt", "test.txt", true));

            assertTrue(client.deletePath("test.txt", false));
            assertTrue(client.deletePath("moved.txt", true));
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}
