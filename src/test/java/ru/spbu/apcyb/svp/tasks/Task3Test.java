package ru.spbu.apcyb.svp.tasks;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class Task3Test {
    private final String existFilePath = ".\\src\\test\\resources\\Test3\\File1.txt";
    private Task3 test;

    @Test
    void testGetStringFromList1() {
        test = new Task3(".\\src\\test\\resources\\Test3");
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");

        assertEquals("1\n2\n3\n", Task3.getString(testList));
    }

    @Test
    void testGetStringFromList2() {
        test = new Task3(".\\src\\test\\resources\\Test3");
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");

        assertNotEquals("1\n2\n", Task3.getString(testList));
    }

    @Test
    void testGetList1() {
        List<String> expected = new ArrayList<>();
        test = new Task3(".\\src\\test\\resources\\Test3");
        expected.add(".\\src\\test\\resources\\Test3\\Directory");
        expected.add(".\\src\\test\\resources\\Test3\\Directory\\File2.txt");
        expected.add(".\\src\\test\\resources\\Test3\\File1.txt");
        List<String> actual = test.getList();

        assertEquals(expected, actual);
    }

    @Test
    void testGetList2() {
        List<String> expected = new ArrayList<>();
        test = new Task3(".\\src\\test\\resources\\Test3\\Directory");
        expected.add(".\\src\\test\\resources\\Test3\\Directory");
        expected.add(".\\src\\test\\resources\\Test3\\Directory\\File2.txt");
        expected.add(".\\src\\test\\resources\\Test3\\File1.txt");

        assertNotEquals(expected, test.getList());
    }

    @Test
    void testDirectoryVerification1() {
        test = new Task3(".\\src\\test\\resources\\TestDirectory");
        assertThrows(FileNotFoundException.class, () -> Task3.directoryVerification(""));
    }

    @Test
    void testDirectoryVerification2() {
        test = new Task3(".\\src\\test\\resources\\TestDirectory");
        assertThrows(IllegalArgumentException.class, () -> Task3.directoryVerification(existFilePath));
    }
}
