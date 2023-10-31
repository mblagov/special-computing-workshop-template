package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.*;

public class WorkWithFiles {
    public void getFilesInDirectory(String dir, String saveIn) {
        Path dirPath = Path.of(dir);
        try (var files = Files.walk(dirPath)) {
            Path savePath = Path.of(saveIn);
            Files.writeString(savePath, "");
            files.forEach(file -> {
                try {
                    Files.writeString(savePath, file.toString() + "\n", StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WorkWithFiles wwf = new WorkWithFiles();
        wwf.getFilesInDirectory("src\\main\\resources\\testDir",
                "src\\main\\resources\\checkIn");

    }
}
