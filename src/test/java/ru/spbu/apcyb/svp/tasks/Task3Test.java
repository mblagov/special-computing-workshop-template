package ru.spbu.apcyb.svp.tasks;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task3.Task3;

/**
 * Тесты для задания 3.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task3Test {

  @Test
  void test1Task3() throws IOException {
    var path = ".\\src";
    Assertions.assertNotNull(Task3.checkCatalogue(Paths.get(path)));
  }

  @Test
  void test2Task3() throws IOException {
    var path = ".\\src\\test\\resources\\task3TestFolder";
    var file = ".\\src\\test\\resources\\task3FileResult.txt";
    Task3.getFileTree(path, file);
    var scanner = new Scanner(new FileReader(file));
    List<String> fileContent = new ArrayList<>();
    while (scanner.hasNextLine()) {
      fileContent.add(scanner.nextLine());
    }
    var expected = new String[] {
        path + "\\folder1",
        path + "\\folder2",
        path + "\\file1.txt",
        path + "\\file2.txt",
        path + "\\file3.txt",
    };
    for (int i = 0; i < expected.length; i++) {
      Assertions.assertTrue(fileContent.contains(expected[i]));
    }
    scanner.close();
  }
}
