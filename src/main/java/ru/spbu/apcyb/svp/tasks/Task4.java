package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {
    private static final Logger logger = LogManager.getLogger(Task4.class);

    public static List<Double> getArgumentsListFromFile(String fileName) {
        List<Double> argumentsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String argumentsString;
            double[] argumentsDoubleRow;
            while ((argumentsString = bufferedReader.readLine()) != null) {
                argumentsDoubleRow = Arrays.stream(argumentsString.trim().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
                for (double arg : argumentsDoubleRow)
                    argumentsList.add(arg);
            }
        } catch (IOException e) {
            logger.error("Ошибка!", e);
        }
        return argumentsList;
    }

    public static List<Double> singleThreadComputation(List<Double> argumentsList) {
        List<Double> tanList = new ArrayList<>();
        for (Double arguments : argumentsList) {
            tanList.add(Math.tan(arguments));
        }
        return tanList;
    }

    public static List<Double> multiThreadComputation(List<Double> argumentsList, int numberOfThreads) throws IOException, ExecutionException {
        if (numberOfThreads <= 0) {
            throw new IOException("Количество потоков должно быть положительно!");
        }
        List<Double> tanList = new ArrayList<>();
        List<Future<Double>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        try {
            for (int i = 0; i < argumentsList.size(); i++) {
                int finalI = i;
                Future<Double> values = executor.submit(() -> Math.tan(argumentsList.get(finalI)));
                futures.add(values);
            }
        } finally {
            executor.shutdown();
        }
        for (Future<Double> future : futures) {
            try {
                tanList.add(future.get());
            } catch (Exception e) {
                logger.error("Произошла ошибка!", e);
            }
        }
        return tanList;
    }

    public static void writeToFile(String fileName, List<Double> argumentsList, List<Double> values) {
        if (Files.exists(Path.of(fileName))) {
            throw new IllegalArgumentException("Такой файл уже существует!");
        }

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < values.size(); i++) {
                fileWriter.write("x = " + argumentsList.get(i) + ", tan(x) =  " + values.get(i) + ";\n");
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка!", e);
        }
    }

}