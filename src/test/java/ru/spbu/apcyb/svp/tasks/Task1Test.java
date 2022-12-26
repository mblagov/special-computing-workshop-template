package ru.spbu.apcyb.svp.tasks;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 1.
 */
public class Task1Test {
  @Test
  void ParseArr1() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("asd 2 1");

    boolean thrown = false;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = true;
    }

    assertTrue(thrown);
  }

  @Test
  void ParseArr2() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("");

    boolean thrown = false;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = true;
    }

    assertTrue(thrown);
  }

  @Test
  void ParseArr3() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("-5 2 1");

    boolean thrown = false;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = true;
    }

    assertTrue(thrown);
  }

  @Test
  void ParseArr4() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("3+2 1+1 2+1");

    boolean thrown = false;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = true;
    }

    assertTrue(thrown);
  }

  @Test
  void ParseArr5() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("0 1+1 2+1");

    boolean thrown = false;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = true;
    }

    assertTrue(thrown);

  }

  @Test
  void IsWorking() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("20 20 20");

    boolean thrown = false;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = true;
    }

    assertTrue(thrown);

  }

  @Test
  void ParseArr7() {

    ArrayList<String> list = new ArrayList<String>();
    list.add("20 3 2");
    boolean thrown = true;

    try {
      Task1.ParseArr(list);
    } catch (Exception e) {
      thrown = false;
    }

    assertTrue(thrown);

  }

}
