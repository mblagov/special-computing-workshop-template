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
        var list = getListFiles(fileName);
        if (list == null) {
            throw new FileNotFoundException("Введенной директории не существует!");
        }
        return true;
    }


    private static File[] getListFiles(String path) {
        File root = new File(path);
        return root.listFiles();
    }

    private static void writeTabulation(int tabulation) throws IOException {
        for (int i = 0; i < tabulation; i++) {
            fileWriter.write(' ');
        }
    }

    private static void directoryTraversal(String path, int tabulation) throws IOException {
        var list = getListFiles(path);
        for (File f : list) {
            writeTabulation(tabulation);
            if (f.isDirectory()) {
                fileWriter.write("Директория: " + f.getPath() + "\n");
                directoryTraversal(f.getAbsolutePath(), tabulation + 1);
            } else {
                fileWriter.write("Файл: " + f.getName() + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Введено менее 2 аргументов в функцию");
        }
        if (args.length > 2) {
            throw new ArrayIndexOutOfBoundsException("Введено более 2 аргументов в функцию");
        }
        File file = new File(args[1]);
        checkInputs(args[0], file);
        fileWriter = new FileWriter(file, false);
        directoryTraversal(args[0], 0);
        fileWriter.close();
    }
}