package ru.spbu.apcyb.svp.tasks.fifth;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WordCounterTest {
    @Test
    void testByWarAndPeace() throws IOException {
        Path statisticFile = Path.of(WordCounter.STATISTIC_FILENAME);
        Path resultDirectory = Path.of(WordCounter.WORDS_DIRECTORY);

        try {
            String filename = "War_and_Peace_book.txt";
            WordCounter.countWords2Files(filename);

            assertTrue(statisticFile.toFile().exists());
            assertTrue(resultDirectory.toFile().isDirectory());
        } finally {
            // Delete created files recursively
            try (Stream<Path> walk = Files.walk(resultDirectory)) {
                walk.sorted().map(Path::toFile).forEach(File::deleteOnExit);
            }
            statisticFile.toFile().deleteOnExit();
        }
    }
}
