package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 4.
 */
class Task4Test {

    /**
     * 100 раз читает значение из data.
     * Cчитает его тангенс и ищет его в файле singleThreadTestResFile.
     */
    @Test
    void singleThreadTest() throws IOException {
        int numberOfLines = 100;
        FileWriter singleThreadTestResFile = new FileWriter("singleThreadTest.txt", false);
        Task4.singleThread(singleThreadTestResFile, numberOfLines);
        Path filePath1 = Path.of("data.txt");
        Path filePath2 = Path.of("singleThreadTest.txt");
        BufferedReader fromData = new BufferedReader(new FileReader(filePath1.toFile()));
        BufferedReader fromSingleThreadTest = new BufferedReader(new FileReader(filePath2.toFile()));
        String currentLine = fromSingleThreadTest.readLine();
        String res = "";
        boolean check = true;
        for (int i = 0; i < numberOfLines; i++) {
            res = fromData.readLine();
            res = String.valueOf(Math.tan(Double.parseDouble((res))));
            if (!currentLine.contains(res)) {
                check = false;
            }
            assertTrue(check);
        }
    }

    /**
     * Считывает строку из multiThreadTest, разбивает ее на числа.
     * и ищет их в singleThreadTest(т.к его уже проверили)
     * если нашел, то checkedAmount++
     */
    @Test
    void multiThreadTest() throws IOException {
        FileWriter multiThreadTestResFile = new FileWriter("multiThreadTest.txt", false);
        Path filePath1 = Path.of("singleThreadTest.txt");
        Path filePath2 = Path.of("multiThreadTest.txt");
        BufferedReader fromSingleThreadTest = new BufferedReader(new FileReader(filePath1.toFile()));
        String reference = fromSingleThreadTest.readLine();
        Task4.multiThread(multiThreadTestResFile, 100,10);
        multiThreadTestResFile.close();
        int seenAmount = 0;
        int checkedAmount = 0;
        try (BufferedReader fromMultiResTest = new BufferedReader(new FileReader(filePath2.toFile()))) {
            String currentLine = "";
            while ((currentLine = fromMultiResTest.readLine()) != null) {
                seenAmount++;
                if (!currentLine.equals("")) {
                    String[] splitCurrentLine = currentLine.split(" ");
                    for (String linePiece : splitCurrentLine) {
                        if (reference.contains(linePiece))
                            checkedAmount++;
                    }
                }
            }
            assertEquals(seenAmount, checkedAmount);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @Test
    void FileNotFoundTest() throws IOException {
        FileWriter res = new FileWriter("Test.txt", false);
        res.close();
        FileNotFoundException thrown =
                assertThrows(FileNotFoundException.class, () -> Task4.singleThread(res, 100));
        assertEquals("", thrown.getMessage());
    }

}

