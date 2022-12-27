package ru.spbu.apcyb.svp.tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

  @Test
  void fFinderTest1() {
    boolean work = true;
    File file = new File("answ.txt");
    String direct = "..//";
    try {
      Task3.fFinder(direct, file, 0);
    } catch (IOException thrown) {
      work = false;
    }
    assertTrue(work);
  }

  @Test
  void fFinderTest2() {
    File file1 = new File("answ.txt");
    String direct1 = "IAmNotExist";

    /* проверка на существование директории
     */
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task3.fFinder(direct1, file1, 0));
    assertEquals("java.io.FileNotFoundException: Директория не существует", thrown.toString());
  }

  @Test
  void fFinderTest3() {
    /* проверка на то что записывающий файл - директория
     */
    String direct2  = "..//";
    File file2 = new File("ans.txt");
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task3.fFinder(direct2, file2, 0));
    assertEquals("java.io.FileNotFoundException: Записывающий файл - директория.", thrown.toString());
  }

  @Test
  void fFinderTest4() {
    /* проверка на то что записывающий файл - директория
     */
    String direct3  = "..//";
    File file3 = new File("..//");
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task3.fFinder(direct3, file3, 0));
    assertEquals("java.io.FileNotFoundException: Записывающий файл - директория.", thrown.toString());
  }

}
