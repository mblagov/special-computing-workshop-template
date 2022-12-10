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
     * @param MASS - массив, содержащий само слово и число его вхождений в текст.
     *             MASS[0] - слово.
     *             MASS[1] - число вхождений.
     */
    public static void wordsToExistingFile(String[] MASS) throws FileNotFoundException {
        try (BufferedWriter wordFile = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream((MASS[0] + ".txt"))))) {
            for (int i = 0; i < Integer.parseInt(MASS[1]); i++) {
                wordFile.write(MASS[0]);
                wordFile.write("\n");
            }
        } catch (IOException e) {
            throw new FileNotFoundException("file for word " + MASS[0] + "did not open");
        }
    }

    /**
     * Создает файл для каждого слова, с ним же в названии.
     *
     * @param MASS - массив, содержащий само слово и число его вхождений в текст.
     * Принимает весь массив, чтобы отправить его в wordsToExistingFile.
     *             MASS[0] - слово.
     */
    public static void wordsToNonExistingFile(String[] MASS) throws FileNotFoundException {
        try (FileWriter wordFile = new FileWriter(MASS[0] + ".txt", true)) {
            wordsToExistingFile(MASS);
        } catch (IOException ex) {
            throw new FileNotFoundException("new file for new word " + MASS[0] + "did not open");
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
    public static void textFileSort(String dataFileName, String countsFileName) throws FileNotFoundException {
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
                            e.printStackTrace();
                        }
                        String[] MASS = String.valueOf(word).split("=");
                        if (Files.exists(Path.of(MASS[0] + ".txt"))) {
                            CompletableFuture.runAsync(() -> {
                                try {
                                    wordsToExistingFile(MASS);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            CompletableFuture.runAsync(() -> {
                                try {
                                    wordsToNonExistingFile(MASS);
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
        textFileSort("aaaaaaaaaa.txt", "counts.txt");
    }


}

