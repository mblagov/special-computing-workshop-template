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

    public static void walkFileTree(String path, FileWriter fileWriter) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
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
        FileWriter fileWriter = new FileWriter(fileWriterName, false);
        walkFileTree(path, fileWriter);
        fileWriter.flush();
        return true;
    }

    public static void main(String[] args) throws IOException {
        walkFileTree("[[", "task3.txt");
    }
}
