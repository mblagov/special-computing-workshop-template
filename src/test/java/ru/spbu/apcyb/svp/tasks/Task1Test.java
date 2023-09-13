package ru.spbu.apcyb.svp.tasks;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;


class Task1Test {
    private Task1 ts;

    @BeforeEach
    public void initTask() {
        ts = new Task1();
    }

    @Test
    void combinations1() {
        Integer[] arr = {200, 100, 50};
        int actual = ts.getCombinations(620, 200, "", arr);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void combinations2() {
        Integer[] arr = {10, 6};
        int actual = ts.getCombinations(5, 10, "", arr);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void combinations3() {
        Integer[] arr = {3, 2};
        int actual = ts.getCombinations(5, 3, "", arr);
        int expected = 1;
        assertEquals(expected, actual);
    }


    @Test
    void combinations4() {
        Integer[] arr = {2, 1};
        int actual = ts.getCombinations(4, 2, "", arr);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void combinations5() {
        Integer[] arr = {1, 2};
        int actual = ts.getCombinations(4, 2, "", arr);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void combinations7() {
        Integer[] arr = {1, 2};
        int actual = ts.getCombinations(-1, 2, "", arr);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void combinations8() {
        Integer[] arr = {};
        int actual = ts.getCombinations(200, 0, "", arr);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void checkCombinations1() {
        Integer[] arr = {1, 2};
        ts.getCombinations(4, 2, "", arr);
        ArrayList<String> expected = new ArrayList<>();
        expected.add(" 1  1  1  1 ");
        expected.add(" 2  1  1 ");
        expected.add(" 2  2 ");
        assertEquals(expected, ts.getCombinations());
    }

    @Test
    void checkCombinations2() {
        String arr = "7 4 2 9";
        ts.getCombinations(14, 9, "", ts.parseNominal(arr));
        ArrayList<String> expected = new ArrayList<>();
        expected.add(" 7  7 ");
        expected.add(" 4  4  4  2 ");
        expected.add(" 4  4  2  2  2 ");
        expected.add(" 4  2  2  2  2  2 ");
        expected.add(" 2  2  2  2  2  2  2 ");
        assertEquals(expected, ts.getCombinations());
    }

    @Test
    void checkDuplicate() {
        String arr = "2 1 1 1";
        Integer[] parsedArr = ts.parseNominal(arr);
        int actual = ts.getCombinations(4, parsedArr[0], "", parsedArr);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100 50 20", "a", ""})
    void checkNom(String arg) {
        assertThrows(IllegalArgumentException.class, () -> {
            ts.parseNominal(arg);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"100 50 20", "100 20 50", "3 2", "1"})
    void checkNom2(String arg) {
        assertDoesNotThrow(() -> {
            ts.parseNominal(arg);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100", "a", " "})
    void checkSum(String arg) {
        assertThrows(IllegalArgumentException.class, () -> {
            ts.parseNominal(arg);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "1 1 1", "1000"})
    void checkSum2(String arg) {
        assertDoesNotThrow(() -> {
            ts.parseNominal(arg);
        });
    }
}