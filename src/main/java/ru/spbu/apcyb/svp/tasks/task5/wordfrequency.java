package ru.spbu.apcyb.svp.tasks.task5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class wordfrequency {
    private static final Logger logger = Logger.getLogger(wordfrequency.class.getName());
    public static void main(String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException("Неверное число аргументов");
        }

        try {

            Path inputPath = Path.of(args[0]);
            Path outputPath = Path.of(args[1]);
            String outputString = inputPath.toString();

            BufferedReader reader = Files.newBufferedReader(inputPath);

            Map<String, Long> wordCounts = reader.lines()
                    .map(line -> line.replaceAll("[,.?!—:;«»)(]", ""))
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

            BufferedWriter writer = Files.newBufferedWriter(outputPath);
            wordsCounter(wordCounts, writer);
            writer.close();

            Files.createDirectories(Paths.get(outputString + "/words"));
            writingWordsToFileInDir(wordCounts, outputString + "/words/");
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public static void wordsCounter(Map<String, Long> wordCounts, BufferedWriter writer) throws IOException{
        for (Map.Entry<String, Long> entry : wordCounts.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }

    public static void writingWordsToFileInDir(Map<String, Long> wordCounts, String writingFile) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            Path directory = Path.of(writingFile);
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            wordCounts.forEach((word, count) -> CompletableFuture.runAsync(
                    () -> writingWordsInTheirFile(word, count, writingFile), executor).join());
        } catch (IOException e) {
            logger.info("Ошибка при записи в файл: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    public static void writingWordsInTheirFile(String word, Long count, String strWritingFile) {
        Path writingFile = Path.of(strWritingFile + word + ".txt");
        try (BufferedWriter bw = Files.newBufferedWriter(writingFile)) {
            for (long i = 0; i < count; i++) {
                bw.write(word + " ");
            }
        } catch (IOException e) {
            logger.info("Ошибка при записи слова ( " + word + " ) в свой файл: "
                    + e.getMessage());
        }
    }

}