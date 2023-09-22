package ru.spbu.apcyb.svp.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task5 {
    private static final Logger logger = LogManager.getLogger(Task5.class);
    public static void directoryVerification(Path path) throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Выбранной директории не существует!");
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Это не директория!");
        }
    }

    public static void filesVerification(Path path) throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Такого файла не существует!");
        }
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Этот файл является директорией!!");
        }
    }

    public static Map<String, Integer> countWordsInFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        filesVerification(path);
        List<String> wordsList = Files.lines(path).flatMap(s -> Stream.of(s.split("\\p{Punct}|\\s+")).filter(p -> p.length() != 0)).collect(Collectors.toList());

        return wordsList.stream().collect(HashMap::new, (m, c) -> m.put(c, m.containsKey(c) ? (m.get(c) + 1) : 1), HashMap::putAll);
    }

    public static void writeToFile(String filePath, Map<String, Integer> map) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            throw new IllegalArgumentException("Файл уже существует!");
        }
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Файл является директорией!");
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (var entry : map.entrySet()) {
                fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка!", e);
        }
    }

    public static void createFiles(String directoryPath, Map<String, Integer> map, int numberOfThreads) throws FileNotFoundException {
        Path path = Paths.get(directoryPath);
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Количество потоков должно быть > 0!");
        }
        directoryVerification(path);
        Map<String, CompletableFuture<String>> completableFutureMap = new HashMap<>();
        for (var entry : map.entrySet()) {
            completableFutureMap.put(entry.getKey(), CompletableFuture.supplyAsync(() -> {
                StringBuilder bld = new StringBuilder();
                for (int i = 0; i < entry.getValue(); i++) {
                    bld.append(entry.getKey()).append(" ");
                }
                return bld.toString();
            }));
        }

        for (var entryFuture : completableFutureMap.entrySet()) {
            try (FileWriter fileWriter = new FileWriter(directoryPath + "\\" + entryFuture.getKey() + ".txt")) {
                fileWriter.write(entryFuture.getValue().get());
            } catch (Exception e) {
                logger.error("Произошла ошибка!", e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var result = Task5.countWordsInFile(".\\src\\test\\resources\\Test5\\Test1.txt");
        Task5.writeToFile(".\\src\\test\\resources\\Test5\\counts.txt", result);
        Task5.createFiles(".\\src\\test\\resources\\Test5\\CreatedFiles", result, 10);
    }
}
