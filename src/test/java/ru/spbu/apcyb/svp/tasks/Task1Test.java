package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
public class Task1Test {

  @Test
  void inDenom_ints() {
    Long[] exp = new Long[]{34L, 2L, 1L};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inDenom("1 34 2")));
  }

  @Test
  void inDenom_double() {
    Long[] exp = new Long[]{};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inDenom("1 34,1 2")));
  }

  @Test
  void inDenom_empty() {
    Long[] exp = new Long[]{};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inDenom("")));
  }

  @Test
  void inDenom_str() {
    Long[] exp = new Long[]{};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inDenom("2 a 3")));
  }

  @Test
  void inDenom_negative() {
    Long[] exp = new Long[]{};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inDenom("1 34 -2")));
  }

  @Test
  void variants_right() {
    Long[] denom = new Long[]{100L, 50L, 20L};
    assertEquals(6, Task1.variants(denom, 200, denom[0], ""));
  }

  @Test
  void variants_noChange() {
    Long[] denom = new Long[]{100L};
    assertEquals(0, Task1.variants(denom, 110, denom[0], ""));
  }

  @Test
  void inSum_int() {
    assertEquals(15, Task1.inSum("15"));
  }

  @Test
  void inSum_str() {
    assertEquals(-1, Task1.inSum("a"));
  }

  @Test
  void inSum_double() {
    assertEquals(-1, Task1.inSum("1,5"));
  }

  @Test
  void inSum_negative() {
    assertEquals(-300, Task1.inSum("-300"));
  }

  @Test
  void inSum_empty() {
    assertEquals(-1, Task1.inSum(""));
  }
}
