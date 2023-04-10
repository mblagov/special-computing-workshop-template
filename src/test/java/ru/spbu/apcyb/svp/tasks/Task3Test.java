package ru.spbu.apcyb.svp.tasks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
  private final String catalogue =
      "C:\\Users\\zeron\\programming-projects"
          + "\\special-computing-workshop-template\\src\\test\\resources\\testDir";
  private final String filename = "untitled.txt";
  private List<String> list;

  @BeforeAll
  void listInit() {
    list = new ArrayList<>();
    list.add(
        "C:\\Users\\zeron\\programming-projects"
            + "\\special-computing-workshop-template\\src\\test\\resources\\testDir\\directory");
    list.add(
        "C:\\Users\\zeron\\programming-projects"
            + "\\special-computing-workshop-template\\src\\test\\resources\\testDir\\file.txt");
  }

  @Test
  void task3Test1() throws IOException {
    var answer = Task3.checkCatalogue(Paths.get(catalogue));
    Assertions.assertEquals(list.get(0), answer.get(0));
    Assertions.assertEquals(list.get(1), answer.get(1));
  }
}
