package ru.spbu.apcyb.svp.tasks.task3;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

// javac task3.java
// java task3 /путь/к/директории /путь/к/целевому/файлу

public class task3 {
    public static void main(String[] args) throws IOException{

        if (args.length != 2) {
            throw new IllegalArgumentException("Неверное число аргументов");
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new FileNotFoundException("Некорректная директория");
        }
        if (new File(args[1]).isDirectory()) {
            throw new FileSystemException("Некорректный целевой файл");
        }

        String sourceDirectoryPath = args[0];
        String targetFilePath = args[1];

        File sourceDirectory = new File(sourceDirectoryPath);
        File targetFile = new File(targetFilePath);

        try {
            FileWriter writer = new FileWriter(targetFile);
            writeToFile(sourceDirectory, "", writer);
            writer.close();
            System.out.println("Список файлов был записан в " + targetFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void writeToFile(File directory, String indent, FileWriter writer) throws IOException {
        writer.write(indent + directory.getName() + "/\n");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    writeToFile(file, indent + "  ", writer);
                }
                else {
                    writer.write(indent + "  " + file.getName() + "\n");
                }
            }
        }
    }
}
