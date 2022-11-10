package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {
    /**
     * Метод, в одного считающий все числа из файла и записывающий их в файл результата.
     * Считает время выполнения.
     *
     * @param singleThreadResFile - файл с результатами вычислений.
     * @param numberOfLines       - сколько строк из файла data нужно обратодать.
     */
    public static void singleThread(FileWriter singleThreadResFile, int numberOfLines) throws IOException {
        Logger logger = Logger.getLogger(Task4.class.getName());
        long start = System.nanoTime();
        Path filePath = Path.of("data.txt");
        try (singleThreadResFile; singleThreadResFile; BufferedReader fromData = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine;
            for (int i = 0; i < numberOfLines; i++) {
                currentLine = fromData.readLine();
                currentLine = String.valueOf(Math.tan(Double.parseDouble(currentLine)) + " ");
                singleThreadResFile.write(currentLine);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("не нашелся файл data");
        }
        long finish = System.nanoTime();
        long time = finish - start;
        logger.info("SingleThread:  " + time);
    }

    /**
     * Метод, запускающий 10 "дежурных" потоков multiThreadTan для досчета чисел и записи в файл.
     * Считает время выполнения.
     *
     * @param multiThreadResFile - файл с результатами вычислений.
     * @param numberOfLines      - сколько строк из файла data нужно обратодать, но меньше в 10 раз.
     * @param numberOfThreads    - число потоков в ThreadPool.
     */
    public static void multiThread(FileWriter multiThreadResFile, int numberOfLines, int numberOfThreads) {
        Logger logger = Logger.getLogger(Task4.class.getName());
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        long start = System.nanoTime();
        try {
            for (int i = 0; i < numberOfLines / numberOfThreads; i++) {
                for (int j = 1; j <= numberOfThreads; j++) {
                    executorService.execute(new multiThreadTan(j + i * numberOfThreads, multiThreadResFile));
                }
            }
        } finally {
            executorService.shutdown();
        }

        long finish = System.nanoTime();
        long time = finish - start;
        logger.info("MultiThread:   " + (time));
    }

    /**
     * Main.
     * Создает файлы для результатов вычислений.
     * Вызывает методы multiThread и singleThread.
     */
    public static void main(String[] args) throws IOException {
        try (FileWriter multiThreadResFile = new FileWriter("MultiRes.txt", false)) {
            multiThread(multiThreadResFile, 1000, 10);
        }
        FileWriter singleThreadResFile = new FileWriter("SingleRes.txt", false);
        singleThread(singleThreadResFile, 1000);
    }
}