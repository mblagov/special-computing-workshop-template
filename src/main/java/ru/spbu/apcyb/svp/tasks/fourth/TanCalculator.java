package ru.spbu.apcyb.svp.tasks.fourth;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TanCalculator {
    private final List<Double> inputs;
    private final List<Double> outputs;

    // maximum number of threads
    private static final int MAX_THREADS = 10;

    private TanCalculator(List<Double> inputs) {
        this.inputs = inputs;
        outputs = new ArrayList<>(inputs.size());
    }

    /**
     * Calculate all input values with fixed number of threads.
     *
     * @throws ExecutionException   if the computation threw an exception.
     * @throws InterruptedException if some thread was interrupted while waiting.
     */
    public void runMultiThread() throws ExecutionException, InterruptedException {
        if (inputs.isEmpty()) {
            return;
        }

        outputs.clear();

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        ArrayList<Future<Double>> futures = new ArrayList<>(inputs.size());

        try {
            for (final Double input : inputs) {
                futures.add(executor.submit(() -> calculate(input)));
            }

            for (Future<Double> future : futures) {
                outputs.add(future.get());
            }
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Calculate all input values with fixed number of threads using task batching.
     *
     * @throws ExecutionException   if the computation threw an exception.
     * @throws InterruptedException if some thread was interrupted while waiting.
     */
    public void runThreadBatched() throws ExecutionException, InterruptedException {
        if (inputs.isEmpty()) {
            return;
        }

        outputs.clear();

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        try {
            List<Future<List<Double>>> futures = new ArrayList<>();

            int batchSize = Math.max(1, inputs.size() / MAX_THREADS);
            for (int i = 0; i < inputs.size(); i += batchSize) {
                final List<Double> batch = inputs.subList(i, Math.min(i + batchSize, inputs.size()));

                futures.add(executor.submit(() -> {
                    List<Double> result = new ArrayList<>(batch.size());

                    for (Double input : batch) {
                        result.add(calculate(input));
                    }

                    return result;
                }));
            }

            for (Future<List<Double>> future : futures) {
                outputs.addAll(future.get());
            }
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Calculate all input values in one thread.
     */
    public void run() {
        if (inputs.isEmpty()) {
            return;
        }

        outputs.clear();

        for (Double input : inputs) {
            outputs.add(calculate(input));
        }
    }

    /**
     * Calculate tan of input.
     *
     * @param input input.
     * @return tan of input.
     */
    public static double calculate(double input) {
        return Math.tan(input);
    }

    /**
     * Save working results in file, if results exists. Otherwise, nothing to do.
     *
     * @param file      file to save
     * @param delimiter delimiter for values
     * @throws IllegalArgumentException if the file exists but is a directory rather than a
     *                                  regular file, does not exist but cannot be created, or
     *                                  cannot be opened for any other reason.
     */
    public void saveResults2File(File file, String delimiter) {
        if (outputs.isEmpty()) {
            return;
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Double output : outputs) {
                fw.write(output + delimiter);
            }

            fw.write("\nProcessed " + outputs.size() + " values.");
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not save results to file", e);
        }
    }

    /**
     * Save space-separated working results in file, if results exists. Otherwise, nothing to do.
     *
     * @param file file to save
     * @throws IllegalArgumentException if the file exists but is a directory rather than a
     *                                  regular file, does not exist but cannot be created, or
     *                                  cannot be opened for any other reason
     */
    public void saveResults2File(File file) {
        saveResults2File(file, " ");
    }

    /**
     * Reads input numbers form the file and create {@linkplain TanCalculator}.
     *
     * @param file      file with input values, separates by delimiter
     * @param delimiter delimiter of values in file
     * @return A TanCalculator object
     * @throws IllegalArgumentException if the file does not exist, is a directory rather than a
     *                                  regular file, or for some other reason cannot be opened
     *                                  for reading.
     */
    public static TanCalculator fromFile(File file, char delimiter) {
        ArrayList<Double> inputs = new ArrayList<>();

        try (FileReader fr = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();

            int c;
            while ((c = fr.read()) != -1) {
                char ch = (char) c;

                if (ch == delimiter) {
                    inputs.add(Double.parseDouble(sb.toString()));
                    sb.delete(0, sb.length());
                } else {
                    sb.append((char) c);
                }
            }

            // Add last value
            if (!sb.isEmpty()) {
                inputs.add(Double.parseDouble(sb.toString()));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exists: " + file, e);
        }

        return new TanCalculator(inputs);
    }

    /**
     * Reads input numbers form the file and create {@linkplain TanCalculator}.
     *
     * @param file file with space-separated input values
     * @return A TanCalculator object
     * @throws IllegalArgumentException if the file does not exist, is a directory rather than a
     *                                  regular file, or for some other reason cannot be opened
     *                                  for reading.
     */
    public static TanCalculator fromFile(File file) {
        return fromFile(file, ' ');
    }
}
