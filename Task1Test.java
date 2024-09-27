package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
/** Тесты для задания 1. */
class Task1Test {

  @Test
  void strToLongArray_ints() {
    Long[] exp = new Long[] {17L, 3L, 2L};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.strToArray("2 3 17")));
  }

  @Test
  void strToLong_int() {
    assertEquals(5, Task1.strToLong("5"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"2 3 -4", "-1 2 3", "-9 -2 0"})
  void strToLongArray_error(String str) {
    Exception thrown = assertThrows(Exception.class, () -> Task1.strToArray(str));
    assertEquals("Вы ввели не натуральное число.", thrown.getMessage());
  }

  @ParameterizedTest
  @ValueSource(strings = {"a b c", "", "2a", "7, 2 5 3"})
  void strToLongArray_error1(String str) {
    Exception thrown = assertThrows(Exception.class, () -> Task1.strToArray(str));
    assertEquals("Вы ввели не целое число.", thrown.getMessage());
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "a", "2a", "7.25"})
  void strToLong_error(String str) {
    Exception thrown = assertThrows(Exception.class, () -> Task1.strToArray(str));
    assertEquals("Вы ввели не целое число.", thrown.getMessage());
  }

  @ParameterizedTest
  @ValueSource(strings = {"-5", "0"})
  void strToLong_error1(String str) {
    Exception thrown = assertThrows(Exception.class, () -> Task1.strToArray(str));
    assertEquals("Вы ввели не натуральное число.", thrown.getMessage());
  }

  @Test
  void correctCombinations() {
    Long[] nominals = new Long[] {2L, 1L};
    assertEquals(3, Task1.combinations(5, nominals, "", 0));
  }

  @Test
  void noCombinations() {
    Long[] nominals = new Long[] {17L, 21L};
    assertEquals(0, Task1.combinations(62, nominals, "", 0));
  }

  @Test
  void combinations1() {
    Long[] nominals = new Long[] {5L, 3L};
    assertEquals(1, Task1.combinations(13, nominals, "", 0));
  }

  @Test
  void combinations2() {
    Long[] nominals = new Long[] {3L, 5L};
    assertEquals(8, Task1.combinations(113, nominals, "", 0));
  }
}
