package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Задание 4. Многопоточность.
 */
public class Task4 {
    private static Logger logger = Logger.getLogger(Task4.class.getName());

    public static void singleThreadComputation(FileWriter fileWriter, String inputFileName, int numberOfLines) throws IOException {
        long start = System.currentTimeMillis();
        Path filePath = Path.of(inputFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine;
            for (int i = 0; i < numberOfLines; i++) {
                currentLine = bufferedReader.readLine();
                double value = Math.tan(Double.parseDouble(currentLine));
                fileWriter.write(value + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Указанный файл для чтения не найден!");
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении/записи файла!");
        }
        long finish = System.currentTimeMillis();
        long time = finish - start;
        String result = "Время вычисления 1 потока: " + time + " миллисекунды";
        logger.info(result);
    }

    public static void multiThreadComputation(String fileWriterName, String inputFileName, int numberOfLines, int numberOfThreads) throws IOException {

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Double[] values = new Double[numberOfLines];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Path.of(inputFileName).toFile()))) {
            for (int i = 0; i < numberOfLines / numberOfThreads; i++) {
                for (int j = 0; j < numberOfThreads; j++) {
                    String currentLine = bufferedReader.readLine();
                    int copyJ = j;
                    int copyI = i;
                    executorService.execute(() ->
                        values[copyJ + copyI * numberOfThreads] = Math.tan(Double.parseDouble(currentLine))
                    );
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Указанный файл для чтения не найден!");
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении/записи файла!");
        } finally {
            executorService.shutdown();
        }
        try (FileWriter fileWriter = new FileWriter(fileWriterName, false)) {
            for (var s : values) {
                fileWriter.write(s + "\n");
            }
        }
        long finish = System.currentTimeMillis();
        long time = finish - start;
        String result = "Время вычисления для " + numberOfThreads + " потоков: " + time + " миллисекунды";
        logger.info(result);
    }


    public static void main(String[] args) throws IOException {
        String inputFileName = "data.txt";
        try (FileWriter singleThreadFileWriter = new FileWriter("singleThreadRes.txt", false)) {
            singleThreadComputation(singleThreadFileWriter, inputFileName, 10000);
        }
        String multiThreadFileWriterName = "multiThreadRes.txt";
        multiThreadComputation(multiThreadFileWriterName, inputFileName, 10000, 10);
    }
}

