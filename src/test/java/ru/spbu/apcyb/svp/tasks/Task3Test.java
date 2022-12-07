package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static ru.spbu.apcyb.svp.tasks.Task3.walkFileTree;

/**
 * Тесты для задания 3.
 */
public class Task3Test {

    @Test
    void isAbsoluteRef() throws IOException {
        boolean result = walkFileTree("/Users/mayya/IdeaProjects/special-computing-workshop-template/target/classes", "task3.txt");
        Assertions.assertTrue(result);
    }

    @Test
    void isRelativeRef() throws IOException {
        boolean result = walkFileTree("..//", "task3.txt");
        Assertions.assertTrue(result);
    }

    @Test
    void isIncorrectInput() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            walkFileTree("[[", "task3.txt");
        });
    }


}
