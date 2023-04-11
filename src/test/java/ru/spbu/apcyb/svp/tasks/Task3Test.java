package ru.spbu.apcyb.svp.tasks;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task3.Task3;

/**
 * Тесты для задания 3.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task3Test {
  String path;
  String file;

  @BeforeAll
  void init() {
    path = "C:\\Users\\zeron\\programming-projects\\idea-projects"
        + "\\special-computing-workshop-template\\src\\main\\java\\ru\\spbu\\apcyb\\svp";
    file = "C:\\Users\\zeron\\programming-projects\\idea-projects"
        + "\\special-computing-workshop-template\\src\\main\\resources\\task3FileResult";
  }

  @Test
  void test1Task3() throws IOException {
    Assertions.assertNotNull(Task3.checkCatalogue(Paths.get(path)));
  }

  @Test
  void test2Task3() throws IOException {
    Task3.getFileTree(path, file);
    var scanner = new Scanner(new FileReader(file));
    String fileContent = "";
    while (scanner.hasNextLine()) {
      fileContent += scanner.nextLine();
    }
    var list = Task3.checkCatalogue(Paths.get(path));
    String listContent = "";
    for (var string : list) {
      listContent += string;
    }
    Assertions.assertEquals(fileContent, listContent);
  }
}
