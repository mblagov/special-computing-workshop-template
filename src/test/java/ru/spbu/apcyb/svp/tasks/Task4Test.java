package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task4Test {
    private static final Logger logger = Logger.getLogger(Task4Test.class.getName());

    private final String argumentFilePath = ".\\src\\test\\resources\\Test4\\Arguments.txt";
    private final String existResultFilePath = ".\\src\\test\\resources\\Test4\\Result.txt";
    private List<Double> arguments;

    public void setUp(int amountOfNumbers) {
        try (FileWriter fileWriter = new FileWriter(argumentFilePath)) {
            for (int i = 0; i < amountOfNumbers; i++) {
                fileWriter.write(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.arguments = Task4.getArgumentsListFromFile(argumentFilePath);
    }

    @Test
    void testGetArgumentsListFromFile() {
        List<Double> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expected.add((double) i);
        }
        setUp(10);
        assertEquals(this.arguments, expected);
    }

    @Test
    void testMultiThreadComputation() throws IOException, ExecutionException {
        List<Double> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expected.add(Math.tan(i));
        }
        setUp(10);
        List<Double> actual = Task4.multiThreadComputation(this.arguments, 10);
        assertEquals(actual, expected);
    }

    @Test
    void testSingleThreadComputation() {
        List<Double> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expected.add(Math.tan(i));
        }
        setUp(10);
        List<Double> actual = Task4.singleThreadComputation(this.arguments);
        assertEquals(actual, expected);
    }

    @Test
    void testWriteToExistFile() {
        assertThrows(IllegalArgumentException.class, () -> Task4.writeToFile(existResultFilePath, arguments, arguments));
    }

    @Test
    void test10() throws IOException, ExecutionException, InterruptedException {
        setUp(10);
        long start, end;

        start = System.currentTimeMillis();
        Task4.singleThreadComputation(arguments);
        end = System.currentTimeMillis();
        logger.info("Время выполнения для N=10 в однопоточном режиме: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        Task4.multiThreadComputation(arguments, 10);
        end = System.currentTimeMillis();
        logger.info("Время выполнения для N=10 в многопоточном режиме: " + (end - start) + "ms");
        assertEquals(Task4.singleThreadComputation(arguments), Task4.multiThreadComputation(arguments, 10));
    }

    @Test
    void test100() throws IOException, ExecutionException, InterruptedException {
        setUp(100);
        long start, end;

        start = System.currentTimeMillis();
        Task4.singleThreadComputation(arguments);
        end = System.currentTimeMillis();
        logger.info("Время выполнения для N = 100 в однопоточном режиме: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        Task4.multiThreadComputation(arguments, 10);
        end = System.currentTimeMillis();
        logger.info("Время выполнения для N=100 в многопоточном режиме: " + (end - start) + "ms");
        assertEquals(Task4.singleThreadComputation(arguments), Task4.multiThreadComputation(arguments, 10));
    }

    @Test
    void test1000000() throws IOException, ExecutionException, InterruptedException {
        setUp(1000000);
        long start, end;

        start = System.currentTimeMillis();
        Task4.singleThreadComputation(arguments);
        end = System.currentTimeMillis();
        logger.info("Время выполнения для N = 1000000 в однопоточном режиме " + (end - start) + "ms");

        start = System.currentTimeMillis();
        Task4.multiThreadComputation(arguments, 10);
        end = System.currentTimeMillis();
        logger.info("Время выполнения для N=1000000 в многопоточном режиме: " + (end - start) + "ms");
        assertEquals(Task4.singleThreadComputation(arguments), Task4.multiThreadComputation(arguments, 10));
    }
}