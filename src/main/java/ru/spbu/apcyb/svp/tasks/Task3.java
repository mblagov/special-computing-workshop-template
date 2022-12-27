package ru.spbu.apcyb.svp.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    private static final Logger logger = LogManager.getLogger(Task3.class);
    private final File directory;

    public Task3(String path) {
        this.directory = new File(path);
    }

    public static String getString(List<String> directoryList) {
        StringBuilder bld = new StringBuilder();
        for (String filePath : directoryList) {
            bld.append(filePath).append('\n');
        }
        return bld.toString();
    }

    public List<String> getList() {
        return getList(directory);
    }

    private List<String> getList(File directory) {
        List<String> result = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                result.add(f.getPath());
                result.addAll(getList(f));
            }
            if (f.isFile()) {
                result.add(f.getPath());
            }
        }
        return result;
    }

    public static void directoryVerification(String path) throws FileNotFoundException {
        File directory = new File(path);
        if (!directory.exists()) {
            throw new FileNotFoundException("Такой директории не существует!: " + directory.getPath());
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Это не директория!: " + directory.getPath());
        }
    }


    public static void main(String[] args) {
        if (args.length != 2 || Files.exists(Path.of(args[1]))) {
            throw new IllegalArgumentException("Неправильные аргументы программы!");
        }
        try (FileWriter fileWriter = new FileWriter(args[1])) {
            Task3.directoryVerification(args[0]);
            Task3 directoryReader = new Task3(args[0]);
            fileWriter.write(Task3.getString(directoryReader.getList()));
        } catch (IOException e) {
            logger.error("Произошла ошибка!", e);
        }
    }
}