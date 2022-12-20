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

    static class MyException extends RuntimeException {

        public MyException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws IOException {
        countWords("dataForTask5.txt", "count.txt");
    }

    public static void countWords(String dataFile, String answerFile) throws IOException {
        try (Stream<String> reader = Files.lines(Paths.get(dataFile)); BufferedWriter toAnswerFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((answerFile))))) {
            reader.flatMap(line -> Arrays.stream(line.trim().split(" "))).map(String::toLowerCase).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().forEach(word -> {
                try {
                    toAnswerFile.write(word + "\n");
                } catch (IOException e) {
                    throw new MyException("Возникла ошибка при работе с файлом " + answerFile);
                }
                String[] wordAAndItsCount = String.valueOf(word).split("=");
                String currentWord = wordAAndItsCount[0];
                Integer currentWordNumber = Integer.parseInt(wordAAndItsCount[1]);
                CompletableFuture.runAsync(() -> {
                    try {
                        writeWordToFile(currentWord, currentWordNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        } catch (IOException e) {
            throw new FileNotFoundException("Возникла ошибка при чтении " + dataFile + " или " + answerFile);
        }
    }

    public static void writeWordToFile(String word, Integer wordNumber) throws IOException {
        if (Files.exists(Path.of(word + ".txt"))) {
            writeWordToExistingFile(word, wordNumber);
        } else {
            writeWordToNonExistingFile(word, wordNumber);
        }
    }

    public static void writeWordToNonExistingFile(String word, Integer wordNumber) throws FileNotFoundException {
        try (FileWriter wordFile = new FileWriter(word + ".txt", true)) {
            writeWordToExistingFile(word, wordNumber);
        } catch (IOException ex) {
            throw new FileNotFoundException("Произошла ошибка при создании/записи нового файла для слова " + word);
        }
    }

    public static void writeWordToExistingFile(String word, Integer wordNum) throws IOException {
        try (BufferedWriter wordFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((word + ".txt"))))) {
            for (int i = 0; i < wordNum; i++) {
                wordFile.write(word + "\n");
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Произошла ошибка при записи в файл слова " + word);
        }
    }
}
