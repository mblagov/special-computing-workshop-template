package ru.spbu.apcyb.svp.tasks.fifth;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Class for counting words in a text file using Stream API.
 */
public class WordCounter {
    private WordCounter() {
    }

    public static final String STATISTIC_FILENAME = "counts.txt";
    public static final String WORDS_DIRECTORY = "words";


    /**
     * Counts the number of occurrences of each word, writes the results to the file counts.txt
     * and creates separate files for each unique word with their contents.
     * Only words containing only letters (no numbers or spaces) are taken into account.
     *
     * @param fileName: Source text file name
     * @throws IOException: If there is an error reading the source file or writing to the counts.txt file
     */
    public static void countWords2Files(String fileName) throws IOException {
        Map<String, Long> wordsCount;

        // Read words from the file
        try (var lines = Files.lines(Path.of(fileName))) {
            wordsCount = lines.flatMap(line -> Arrays.stream(line.split("\\W+"))) // split by words
                    .map(String::toLowerCase) // to lower case
                    .filter(word -> word.matches("[a-zа-я]+")) // filter only words
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting())); // group by words and count
        }

        // Write statistic to file
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(STATISTIC_FILENAME))) {
            // Use for loop to handle IOExceptions
            for (Map.Entry<String, Long> entry : wordsCount.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }

        //noinspection ResultOfMethodCallIgnored bcs folder structure can be already created, and we don't care.
        Path.of(WORDS_DIRECTORY).toFile().mkdirs(); // create directory for words txt files

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Create and run CompletableFutures
        List<CompletableFuture<Void>> futures = wordsCount.entrySet().stream()
                .map(entry -> CompletableFuture.runAsync(() -> {
                    Path path = Path.of(WORDS_DIRECTORY, entry.getKey() + ".txt");
                    try {
                        Files.write(path, Collections.nCopies(Math.toIntExact(entry.getValue()), entry.getKey()));
                    } catch (IOException e) {
                        //noinspection CallToPrintStackTrace
                        e.printStackTrace(); //NOSONAR
                    }
                }, executor)).toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        executor.shutdown();
    }
}