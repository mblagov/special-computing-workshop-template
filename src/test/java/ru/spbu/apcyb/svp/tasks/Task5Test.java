package ru.spbu.apcyb.svp.tasks;


import static ru.spbu.apcyb.svp.tasks.task5.PrintWords.getLowerBound;
import static ru.spbu.apcyb.svp.tasks.task5.Task5.countAllWords;
import static ru.spbu.apcyb.svp.tasks.task5.Task5.fillCountsFile;
import static ru.spbu.apcyb.svp.tasks.task5.Task5.readInputFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */
class Task5Test {

  private final File testFile = new File("src/main/resources/test.txt");

  @Test
  void readInputFileTest() throws IOException {
    PrintWriter testWriter = new PrintWriter(testFile);
    testWriter.write("I-am?your!-father");
    testWriter.close();
    Map<String, Integer> exp = new HashMap<>();
    exp.put("i", 1);
    exp.put("am", 1);
    exp.put("your", 1);
    exp.put("father", 1);
    Map<String, Integer> testResult = readInputFile(
        testFile.getPath());
    Assertions.assertEquals(exp, testResult);
  }

  @Test
  void fillCountsFileTest() throws IOException {
    PrintWriter testWriter = new PrintWriter(testFile);
    testWriter.write("Get over here here");
    testWriter.close();
    HashSet<String> expected = new HashSet<>(List.of(new String[]{"get: 1", "over: 1", "here: 2"}));
    HashSet<String> actual = new HashSet<>();
    Map<String, Integer> input = readInputFile(testFile.getPath());
    fillCountsFile(input, testFile.getPath());
    BufferedReader bufferedReader = new BufferedReader(new FileReader(testFile));
    for (String line = bufferedReader.readLine(); line != null;
        line = bufferedReader.readLine()) {
      actual.add(line);
    }
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void countAllWordsTest() throws IOException {
    PrintWriter testWriter = new PrintWriter(testFile);
    int num = getLowerBound() + 100;
    testWriter.write(String.join(" ", Collections.nCopies(num, "get over here")));
    testWriter.close();
    String dir = "src/main/resources";
    countAllWords(dir + "/test.txt", dir + "/counts.txt", dir + "/OutTask5");
    BufferedReader getReader = new BufferedReader(
        new FileReader(dir + "/OutTask5/get")
    );
    BufferedReader overReader = new BufferedReader(
        new FileReader(dir + "/OutTask5/over")
    );
    BufferedReader hereReader = new BufferedReader(
        new FileReader(dir + "/OutTask5/here")
    );
    Assertions.assertAll(() -> {
      Assertions.assertEquals(
          String.join(" ", Collections.nCopies(num, "get")),
          getReader.readLine()
      );
      Assertions.assertEquals(
          String.join(" ", Collections.nCopies(num, "over")),
          overReader.readLine()
      );
      Assertions.assertEquals(
          String.join(" ", Collections.nCopies(num, "here")),
          hereReader.readLine()
      );
    });
  }
}
