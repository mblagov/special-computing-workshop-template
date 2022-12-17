package ru.spbu.apcyb.svp.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Задание 3.
 */
public class Task3 {

    private static final Logger logger = LogManager.getLogger(Task3.class);

    public static void walkFileTree(String path, FileWriter fileWriter) throws IOException, FileNotFoundException {
        File f = new File(path);
        if (!f.exists()) {
            logger.fatal("Incorrect input: file (first arg) is not found");
            throw new FileNotFoundException();
        }

        try {
            fileWriter.write(f.getPath() + "\n");
            if (f.listFiles() != null) {
                for (File f1 : f.listFiles()) {
                    walkFileTree(f1.getPath(), fileWriter);
                }
            }

        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    public static boolean walkFileTree(String path, String fileWriterName) throws IOException {
        File f = new File(fileWriterName);
        if (f.isDirectory()) {
            logger.fatal("Incorrect input: FileWriter's name is taken by directory");
            throw new RuntimeException();
        }

        FileWriter fileWriter = new FileWriter(fileWriterName, false);
        walkFileTree(path, fileWriter);
        fileWriter.flush();
        return true;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            logger.fatal("Incorrect input: too many arguments");
            throw new RuntimeException();
        }

        String path = args[0];
        String fileWriterName = args[1];
        walkFileTree(path, fileWriterName);
    }
}
