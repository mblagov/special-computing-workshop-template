package ru.spbu.apcyb.svp.tasks.third;

import com.google.gson.Gson;
import ru.spbu.apcyb.svp.tasks.third.responses.DiskInfoResponse;
import ru.spbu.apcyb.svp.tasks.third.responses.UploadLinkResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class YandexDiskClient {
    private static final URI BASE_URL = URI.create("https://cloud-api.yandex.net/");
    private final HttpClient httpClient;
    private final String[] headers;
    private final Gson gson;

    public YandexDiskClient(String token) {
        this(token, null);
    }

    /**
     * {@link YandexDiskClient} constructor.
     *
     * @param token      OAuth access token for Yandex Disk API.
     * @param httpClient An HttpClient instance used for requests (useful for testing).
     */
    public YandexDiskClient(String token, HttpClient httpClient) {
        this.httpClient = httpClient != null ? httpClient : HttpClient.newHttpClient();
        this.headers = new String[]{
                "Authorization", "OAuth " + token
        };
        this.gson = new Gson();
    }

    /**
     * Performs a request to get disk information.
     *
     * @return DiskInfoResponse with disk space information or error.
     * @throws IOException          if an I/ O error occurs when sending or receiving, or the client has shut down.
     * @throws InterruptedException if the operation is interrupted.
     * @see <a href="https://yandex.ru/dev/disk-api/doc/ru/reference/capacity">GET /v1/disk</a>
     */
    public DiskInfoResponse getDiskInfo() throws IOException, InterruptedException {
        URI uri = BASE_URL.resolve("/v1/disk");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers(headers)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            var diskInfoResponse = gson.fromJson(response.body(), DiskInfoResponse.class);
            diskInfoResponse.setSuccess(true);
            return diskInfoResponse;
        }

        var diskInfoResponse = new DiskInfoResponse();
        diskInfoResponse.setSuccess(false);
        diskInfoResponse.setErrorMessage("Error: " + response.statusCode() + " - " + response.body());
        return diskInfoResponse;
    }

    /**
     * Receiving a link to download a file to Yandex.Disk.
     *
     * @param path Path to the file on Disk that will be downloaded.
     * @return {@link UploadLinkResponse} contains a link to download the file.
     * @throws IOException          if an I/ O error occurs when sending or receiving, or the client has shut down.
     * @throws InterruptedException if the operation is interrupted.
     * @see <a href="https://yandex.ru/dev/disk-api/doc/ru/reference/upload">GET /v1/disk/resources/upload</a>
     */
    public UploadLinkResponse getUploadLink(String path) throws IOException, InterruptedException {
        URI uri = BASE_URL.resolve("/v1/disk/resources/upload?path=" + path);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers(headers)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            UploadLinkResponse uploadLinkResponse = gson.fromJson(response.body(), UploadLinkResponse.class);
            uploadLinkResponse.setSuccess(true);
            return uploadLinkResponse;
        }

        UploadLinkResponse uploadLinkResponse = new UploadLinkResponse();
        uploadLinkResponse.setSuccess(false);
        uploadLinkResponse.setErrorMessage("Error: " + response.statusCode() + " - " + response.body());
        return uploadLinkResponse;
    }

    /**
     * Download the file using the link for upload.
     *
     * @param uploadUrl Download link.
     * @param filePath  Local path to the file to download.
     * @throws FileNotFoundException if the file is not found.
     * @throws URISyntaxException    if uploadUrl is not in the correct format.
     * @throws IOException           if an I/ O error occurs when sending or receiving, or the client has shut down.
     * @throws InterruptedException  if the operation is interrupted.
     * @throws UploadException       if the file is not uploaded.
     */
    public void uploadFile(String uploadUrl, Path filePath) throws URISyntaxException, IOException, InterruptedException, UploadException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uploadUrl))
                .PUT(HttpRequest.BodyPublishers.ofFile(filePath))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 201) {
            throw new UploadException("Error during file upload: " + response.statusCode() + " - " + response.body());
        }
    }

    /**
     * Uploads the file to Yandex.Disk.
     * This is a useful method for running getUploadLink with uploadFile.
     *
     * @param diskPath      Path on Yandex.Disk where the file will be uploaded.
     * @param localFilePath Local path to the file to download.
     * @throws FileNotFoundException if the file is not found.
     * @throws URISyntaxException    if uploadUrl is not in the correct format.
     * @throws IOException           if an I/ O error occurs when sending or receiving, or the client has
     *                               shut down.
     * @throws InterruptedException  if the operation is interrupted.
     * @throws UploadException       if the file is not uploaded or link is not created.
     */
    public void uploadFileToDisk(String diskPath, Path localFilePath) throws IOException, URISyntaxException, InterruptedException, UploadException {
        UploadLinkResponse uploadLinkResponse = getUploadLink(diskPath);

        if (!uploadLinkResponse.isSuccess()) {
            throw new UploadException("Failed to get upload link: " + uploadLinkResponse.getErrorMessage());
        }

        uploadFile(uploadLinkResponse.getHref(), localFilePath);
    }

    /**
     * Deletes a file or directory on Yandex.Disk.
     * This method can delete a file permanently or move it to the trash based on the value of the <code>permanently</code> parameter.
     *
     * @param diskPath    Path to the file or directory that needs to be deleted.
     * @param permanently If <code>true</code>, the file will be deleted permanently without moving to the trash.
     *                    If <code>false</code>, the file will be moved to the trash.
     * @return <code>true</code> if the file or directory was deleted successfully;
     * <code>false</code> otherwise (e.g., if the file does not exist or an error occurs).
     * @throws IOException          if an I/O error occurs when sending or receiving, or the client has shut down.
     * @throws InterruptedException if the operation is interrupted.
     * @see <a href="https://yandex.ru/dev/disk-api/doc/ru/reference/delete">DELETE /v1/disk/resources</a>
     */
    public boolean deletePath(String diskPath, boolean permanently) throws IOException, InterruptedException {
        URI uri = BASE_URL.resolve("v1/disk/resources?path=" + diskPath + "&permanently=" + permanently);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers(headers)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 204;
    }

    /**
     * Moves a file or folder on Yandex.Disk.
     * This method allows you to move a resource from one path to another within Yandex.Disk.
     * If the target path already exists and the overwrite flag is set to <code>true</code>, the existing resource will be replaced.
     *
     * @param from      The source path of the file or folder to be moved.
     * @param path      The target path where the resource will be moved.
     * @param overwrite If true, the existing resource at the target path will be overwritten.
     * @return true if the move operation was successful.
     * @throws IOException          if an I/O error occurs when sending or receiving, or if the client has shut down.
     * @throws InterruptedException if the operation is interrupted.
     * @see <a href="https://yandex.ru/dev/disk-api/doc/ru/reference/move">POST /v1/disk/resources/move</a>
     */
    public boolean movePath(String from, String path, boolean overwrite) throws IOException, InterruptedException {
        URI uri = BASE_URL.resolve("v1/disk/resources/move?from=" + from + "&path=" + path + "&overwrite=" + overwrite);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers(headers)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 201;
    }

    /**
     * Copies a file or folder on Yandex.Disk.
     * This method allows you to create a copy of a resource from one path to another within Yandex.Disk.
     * If the target path already exists and the overwrite flag is set to true, the existing resource will be
     * replaced.
     *
     * @param from      The source path of the file or folder to be copied.
     * @param path      The target path where the resource will be copied.
     * @param overwrite If true, the existing resource at the target path will be overwritten.
     * @return true if the copy operation was successful.
     * @throws IOException          if an I/O error occurs when sending or receiving, or if the client has
     *                              shut down.
     * @throws InterruptedException if the operation is interrupted.
     * @see <a href="https://yandex.ru/dev/disk-api/doc/ru/reference/copy">POST /v1/disk/resources/copy</a>
     */
    public boolean copyPath(String from, String path, boolean overwrite) throws IOException, InterruptedException {
        URI uri = BASE_URL.resolve("/v1/disk/resources/copy?from=" + from + "&path=" + path + "&overwrite=" + overwrite);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers(headers)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 201;
    }
}