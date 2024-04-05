package ru.spbu.apcyb.svp.tasks.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class wordfrequencyTest {

    @Test
    void shortFileTest() throws IOException {
        String[] args = new String[]{"src/test/resources/short_text.txt",
                "src/test/resources/counts_short.txt"};
        wordfrequency.main(args);
        assertEquals(-1, Files.mismatch(
                        Path.of( "src/test/resources/short_textans.txt"),
                        Path.of( "src/test/resources/counts_short.txt")
                )
        );
        assertTrue(Files.exists(Path.of("src/test/resources/words/")));
    }

    @Test
    void incorrectAmountOfArgumentsExceptionTest() {
        String[] args = new String[] {"arg1", "arg2", "arg3"};
        assertThrows(IllegalArgumentException.class, () -> wordfrequency.main(args));
    }

    @Test
    void hugeFileTest() {
        String[] args = new String[]{"src/test/resources/long_text.txt",
                "src/test/resources/counts.txt"};
        wordfrequency.main(args);
        assertTrue(Files.exists(Path.of("src/test/resources/words/")));
    }

}