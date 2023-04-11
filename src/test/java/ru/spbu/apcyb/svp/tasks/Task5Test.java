package ru.spbu.apcyb.svp.tasks;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task5.Task5;

/**
 * Тесты для задания 5.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task5Test {
  @Test
  void test1Task5() throws IOException {
    var stream = Task5.readFile("./src/test/resources/testbook.txt");
    var list = stream.collect(Collectors.toList());
    Assertions.assertTrue(list.contains("bar"));
    Assertions.assertTrue(list.contains("baz"));
    Assertions.assertTrue(list.contains("foo"));
  }

  @Test
  void test2Task5() throws IOException {
    String[] stringArr = new String[]{"foo", "bar", "baz", "foo"};
    var stream = Arrays.stream(stringArr);
    String outFileName = "./src/test/resources/outtest.txt";
    Task5.countWords(stream, outFileName);
    try (var sc = new Scanner(new FileReader(outFileName))) {
      var list = new ArrayList<String>();
      while (sc.hasNextLine()) {
        list.add(sc.nextLine());
      }
      Assertions.assertEquals(3, list.size());
      Assertions.assertTrue(list.contains("foo 2"));
      Assertions.assertTrue(list.contains("bar 1"));
      Assertions.assertTrue(list.contains("baz 1"));
    }
  }

  @Test
  void test3Task5() throws IOException, ExecutionException, InterruptedException {
    var mapOfWOrds = new HashMap<String, Integer>(3);
    mapOfWOrds.put("foo", 2);
    mapOfWOrds.put("bar", 1);
    mapOfWOrds.put("baz", 1);
    Task5.writeWordsToFiles(mapOfWOrds);

    try (var sc = new Scanner(new FileReader("./src/main/resources/wordFiles/foo.txt"))) {
      var line = sc.nextLine();
      Assertions.assertFalse(sc.hasNextLine());
      var split = line.split(" ");
      Assertions.assertEquals(2, split.length);
    }
  }
}
