package ru.spbu.apcyb.svp.tasks.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

;

class TreeTest {

    private Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree(Path.of("./src/Task3/WriterFile"),
                Path.of("./src/Task3"));
    }

    @Test
    void walkerIfFileIsNotExist() {
        tree.setPathOfTheWriterFile(Path.of("/FileIsNotExist"));
        assertThrows(FileNotFoundException.class, () -> tree.walker());
    }

    @Test
    void walker() {
        ArrayList<String> files = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(Path.of("./src/Task3/WriterFile"))) {
            tree.walker();
            while (br.ready()) {
                files.add(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> expected = new ArrayList<>();

        expected.add("Enter to Directory: ./src/Task3");
        expected.add("File name: ./src/Task3/Tree.java");
        expected.add("File name: ./src/Task3/MyFileVisitor.java");
        expected.add("File name: ./src/Task3/WriterFile");
        expected.add("File name: ./src/Task3/TreeTest.java");
        expected.add("Exit from Directory: ./src/Task3");

        assertEquals(expected, files);
    }
}