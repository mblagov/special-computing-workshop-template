package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {

    /**
     * Выводит в существующий для каждого слова файл, с ним же в названии,
     * это слово ровно столько раз, сколько оно встретилось в исходном тексте.
     *
     * @param word - слово.
     * @param wordNumber - число вхождений.
     */
    public static void wordsToExistingFile(String word, Integer wordNumber) throws FileNotFoundException {
        try (BufferedWriter wordFile = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream((word + ".txt"))))) {
            for (int i = 0; i < wordNumber; i++) {
                wordFile.write(word);
                wordFile.write("\n");
            }
        } catch (IOException e) {
            throw new FileNotFoundException("file for word " + word + "did not open");
        }
    }

    /**
     * Создает файл для каждого слова, с ним же в названии.
     *
     * @param word - слово.
     * @param wordNumber - число вхождений.
     */
    public static void wordsToNonExistingFile(String word, Integer wordNumber) throws FileNotFoundException {
        try (FileWriter wordFile = new FileWriter(word + ".txt", true)) {
            wordsToExistingFile(word, wordNumber);
        } catch (IOException ex) {
            throw new FileNotFoundException("new file for new word " + word + "did not open");
        }
    }

    /**
     * Считает число вхождений каждого слова в текст,
     * выводит данные в файл с результатом, и в зависимости от того,
     * встречалось уже слово или нет, вызывает методы wordsToNonExistingFile и wordsToExistingFile.
     *
     * @param dataFileName - название файла с исходным текстом.
     * @param countsFileName - название файла для вывода данных.
     */
    public static void textFileSort(String dataFileName, String countsFileName) throws FileNotFoundException, IOException {
        try (Stream<String> stream = Files.lines(Paths.get(dataFileName));
             BufferedWriter toCountsFile = new BufferedWriter
                     (new OutputStreamWriter(new FileOutputStream((countsFileName))))) {
            stream.flatMap(line -> Arrays.stream(line.trim().split(" ")))
                    .map(word -> word.replaceAll("\\P{L}", " ").trim())
                    .map(String::toLowerCase)
                    .filter(word -> word.length() > 0)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .forEach(word -> {
                        try {
                            toCountsFile.write(word + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException("problems with counts.txt file");
                        }
                        String[] mass = String.valueOf(word).split("=");
                        String currentWord = mass[0];
                        Integer currentWordNumber = Integer.parseInt(mass[1]);
                        if (Files.exists(Path.of(currentWord + ".txt"))) {
                            CompletableFuture.runAsync(() -> {
                                try {
                                    wordsToExistingFile(currentWord,currentWordNumber);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            CompletableFuture.runAsync(() -> {
                                try {
                                    wordsToNonExistingFile(currentWord,currentWordNumber);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    });
        } catch (IOException e) {
            throw new FileNotFoundException("problems with " + dataFileName + " or " + countsFileName + " file");
        }
    }

    public static void main(String[] args) throws IOException {
        textFileSort("aaatask5Reference1.txt", "counts.txt");
    }


}

