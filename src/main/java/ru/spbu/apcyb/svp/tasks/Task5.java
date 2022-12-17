package ru.spbu.apcyb.svp.tasks;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


/**
 * Задание 5.
 */


public class Task5 {
    private static final Logger logger = LogManager.getLogger(Task5.class);

    /**
     * Метод, подсчитывающий число различных между собой слов и записывающий результат в файл
     *
     * @param filename - путь к читаемому файлу
     * @return успешность завершения программы
     */

    public static boolean getWordCount(String filename) throws IOException {
        Path path = Paths.get(filename);

        //создание директории found_words
        String dir = "";
        new File(dir + "found_words").mkdirs();
        FileUtils.cleanDirectory(new File(dir + "found_words"));


        HashMap<String, Integer> hashMap = new HashMap<>();

        //чтение файла и подсчет слов
        try (Stream<String> stream = Files.lines(path)) {
            stream.flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(String::valueOf)
                    .forEach(s -> hashMap.merge(s, 1, Integer::sum)
                    );

            //запись результата в counts.txt
            FileWriter counts = new FileWriter(dir + "found_words/" + "counts.txt", true);
            for (Map.Entry<String, Integer> word : hashMap.entrySet()) {
                counts.write(word.getKey() + " ");
                counts.write(word.getValue() + "\n");
            }
            counts.flush();

            //многопоточная запись каждого слова в свой файл
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            long start = System.nanoTime();
            List<CompletableFuture<Double>> futures = new ArrayList<>();

            hashMap.forEach((word, count) -> CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            FileWriter fileWriter = new FileWriter(dir + "found_words/" + word + ".txt", true);
                            for (int i = 0; i < count; i++) {
                                fileWriter.write(word + " ");
                            }
                            fileWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    , threadPool));
            threadPool.shutdown();
            logger.log(Level.INFO, "(Multi) Executed by " + (System.nanoTime() - start) / 1e+9 + " s");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        getWordCount("notes.txt");
    }
}
