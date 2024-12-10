package ru.spbu.apcyb.svp.tasks.fifth;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WordsCounterTest {
    @Test
    void testByWarAndPeace() throws IOException {
        String filename = "War_and_Peace_book.txt";
        WordCounter.countWords2Files(filename);

        Path statisticFile = Path.of("counts.txt");
        assertTrue(statisticFile.toFile().exists());

        Path resultDirectory = Path.of("words");
        assertTrue(resultDirectory.toFile().isDirectory());

        // Delete created files
        try (Stream<Path> walk = Files.walk(resultDirectory)) {
            //noinspection ResultOfMethodCallIgnored
            walk.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }
        statisticFile.toFile().deleteOnExit();
    }
}
