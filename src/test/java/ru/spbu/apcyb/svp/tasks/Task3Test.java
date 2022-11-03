package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 3.
 */
class Task3Test {

    @Test
    void testFileNotFound() throws IOException {
        FileWriter resFile = new FileWriter("resTest1.txt", false);
        FileNotFoundException thrown =
                assertThrows(FileNotFoundException.class, () -> Task3.getFileTree( resFile, "123"));
        assertEquals("java.io.FileNotFoundException", thrown.toString());
    }

    @Test
    void testTrue() throws IOException {
        FileWriter resFile = new FileWriter("resTest2.txt", false);
        boolean thrown = true;
        try {
            Task3.getFileTree( resFile, "..//");
        } catch (IOException e) {
            thrown = false;
        }
        assertTrue(thrown);
    }

    @Test
    void testFirstLine() throws IOException {
        FileWriter resFile = new FileWriter("resTest3.txt", false);
        try {
            Task3.getFileTree( resFile, "..//");
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
        Path filePath = Path.of("resTest4.txt");
        try (BufferedReader readBuffer = new BufferedReader(new FileReader(filePath.toFile()))) {
            String sCurrentLine = readBuffer.readLine();
            assertEquals("..\\special-computing-workshop-template", sCurrentLine);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }


    @Test
    void testLastLine() throws IOException {
        FileWriter resFile = new FileWriter("resTest4.txt", false);
        try {
            Task3.getFileTree( resFile, "..//");
        } catch (IOException e) {

            throw new FileNotFoundException();
        }
        Path filePath = Path.of("resTest4.txt");
        try (BufferedReader readBuffer = new BufferedReader(new FileReader(filePath.toFile()))) {
            String sCurrentLine = readBuffer.readLine();
            String sNextLine = sCurrentLine;
            while ((sCurrentLine = readBuffer.readLine()) != null) {
                sNextLine = sCurrentLine;
            }
            assertEquals("C:\\Users\\user\\IdeaProjects\\spec", sNextLine);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

}
