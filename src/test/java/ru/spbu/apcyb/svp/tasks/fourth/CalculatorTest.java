package ru.spbu.apcyb.svp.tasks.fourth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

class CalculatorTest {
    ArrayList<Pair<Double, Double>> generateTestData(int limit) {
        ArrayList<Pair<Double, Double>> data = new ArrayList<>(limit);

        Random random = new Random();
        for (int i = 0; i < limit; i++) {
            double input = random.nextDouble(-100, 100);
            data.add(Pair.of(input, TanCalculator.calculate(input)));
        }

        return data;
    }

    Pair<File, File> AddFiles(ArrayList<Pair<Double, Double>> data) {
        try {
            File inputFile = File.createTempFile("input", "test");
            File outputFile = File.createTempFile("expected", "test");

            inputFile.deleteOnExit();
            outputFile.deleteOnExit();

            try (var ifw = new FileWriter(inputFile); var ofw = new FileWriter(outputFile)) {
                for (Pair<Double, Double> pair : data) {
                    ifw.write(pair.getLeft() + " ");
                    ofw.write(pair.getRight() + " ");
                }

                ofw.write("\nProcessed " + data.size() + " values.");
            }

            return Pair.of(inputFile, outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    double checkTime(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000d; // Cast to ms
    }

    double calculateAverageTime(Runnable runnable, int repetitions) {
        double totalTime = 0;

        for (int i = 0; i < repetitions; i++) {
            totalTime += checkTime(runnable);
        }

        return totalTime / repetitions;
    }

    @Test
    void speedTest() throws IOException {
        final int repetitions = 5;  // May throw an error: Cannot allocate memory. Bcs 1_000_000
        final int[] testLimits = new int[]{1, 100, 1_000_000};

        for (int test_limit : testLimits) {
            // Generate test files for calculator
            final Pair<File, File> testFiles = AddFiles(generateTestData(test_limit));
            final TanCalculator calculator = TanCalculator.fromFile(testFiles.getLeft());

            double oneThreadAvgTime = calculateAverageTime(calculator::run, repetitions);
            System.out.println("Average time of one thread for " + test_limit + " values: "
                    + oneThreadAvgTime + " ms");

            double multiThreadAvgTime = calculateAverageTime(() -> {
                try {
                    calculator.runMultiThread();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, repetitions);
            System.out.println("Average time of multi thread for " + test_limit + " values: "
                    + multiThreadAvgTime + " ms");

            double threadBatchedAvgTime = calculateAverageTime(() -> {
                try {
                    calculator.runThreadBatched();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, repetitions);
            System.out.println("Average time of thread batched for " + test_limit + " values: "
                    + threadBatchedAvgTime + " ms\n");

            // Create a temp file for results
            final File resultFile = File.createTempFile("result", "test");
            resultFile.deleteOnExit();

            // Save results
            calculator.saveResults2File(resultFile);

            // Check if results are correct
            assertEquals(Files.readAllLines(resultFile.toPath()),
                    Files.readAllLines(testFiles.getRight().toPath()));
        }
    }

    @Test
    void saveTest() throws IOException {
        final Pair<File, File> testFiles = AddFiles(generateTestData(10));
        final File resultFile = File.createTempFile("result", "test");
        resultFile.deleteOnExit();

        TanCalculator calculator = TanCalculator.fromFile(testFiles.getLeft());
        assertDoesNotThrow(() -> calculator.saveResults2File(resultFile));

        File invalid = new File("!@#$%^&*(invalid path");
        assertThrows(IllegalArgumentException.class, () -> TanCalculator.fromFile(invalid));
    }
}
