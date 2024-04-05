package ru.spbu.apcyb.svp.tasks.task4;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class multithread {
    public static void main(String[] args) throws IOException {

        String inputFilePath = "src/main/resources/input.txt";

        generateNumbersFile(inputFilePath, 100000);

        String outputFilePath = "src/main/resources/output.txt";

        try {

            long startTime = System.nanoTime();
            calculateAndWriteTangentsSinglethreaded(inputFilePath, outputFilePath);
            long endTime = System.nanoTime();
            long singleThreadDuration = endTime - startTime;

            System.out.println("Время выполнения в однопоточном режиме: " + singleThreadDuration + " наносекунд");

            startTime = System.nanoTime();
            calculateAndWriteTangentsMultithreaded(inputFilePath, outputFilePath, 10);
            endTime = System.nanoTime();
            long multiThreadDuration = endTime - startTime;

            System.out.println("Время выполнения в многопоточном режиме: " + multiThreadDuration + " наносекунд");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateNumbersFile(String fileWriteName, int size) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileWriteName, false)) {
            SecureRandom rand = new SecureRandom();
            for (int i = 0; i < size; i++) {
                fileWriter.write(rand.nextFloat(-100, 100) + "\n");
            }
            fileWriter.flush();
        }
    }

    private static List<Double> readNumbersFromFile(String filePath) throws IOException {

        List<Double> numbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            numbers.add(Double.parseDouble(line.trim()));
        }
        reader.close();
        return numbers;
    }

    public static boolean  calculateAndWriteTangentsSinglethreaded(String inputFilePath, String outputFilePath) throws IOException {

        List<Double> numbers = readNumbersFromFile(inputFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        for (double number : numbers) {
            double tangent = Math.tan(number);
            writer.write(Double.toString(tangent));
            writer.newLine();
        }
        writer.close();

        return true;
    }

    public static boolean calculateAndWriteTangentsMultithreaded(String inputFilePath, String outputFilePath, int size) throws IOException {

        List<Double> numbers = readNumbersFromFile(inputFilePath);
        ExecutorService executor = Executors.newFixedThreadPool(size);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

        for (double number : numbers) {
            executor.submit(() -> {
                double tangent = Math.tan(number);
                synchronized (writer) {
                    try {
                        writer.write(Double.toString(tangent));
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

        return true;
    }
}
