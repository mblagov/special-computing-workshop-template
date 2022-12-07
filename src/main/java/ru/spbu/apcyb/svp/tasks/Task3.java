package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

/**
 * Задание 3.
 */
public class Task3 {

    private static FileWriter fileWriter;

    private static boolean checkInputs(String fileName, File startDirectory) throws IOException {
        if (startDirectory.isDirectory()) {
            throw new NotDirectoryException("Введенный путь для объекта для записями является директорией!");
        }
        File root = new File(fileName);
        File[] list = root.listFiles();
        if (list == null) {
            throw new FileNotFoundException("Введенной директории не существует!");
        }
        return true;
    }

    private static void directoryTraversal(String path, int tabulation) throws IOException {
        File root = new File(path);
        File[] list = root.listFiles();
        for (File f : list) {
            for (int i = 0; i < tabulation; i++) {
                fileWriter.write(' ');
            }
            if (f.isDirectory()) {
                fileWriter.write("Директория: " + f.getPath() + "\n");
                directoryTraversal(f.getAbsolutePath(), tabulation + 1);
            } else {
                fileWriter.write("Файл: " + f.getName() + "\n");
            }
        }

    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[1]);
        checkInputs(args[0], file);
        fileWriter = new FileWriter(file, false);
        directoryTraversal(args[0], 0);
        fileWriter.close();
    }
}