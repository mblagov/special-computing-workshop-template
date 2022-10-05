package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  void inNotes_ints() {
    Long[] exp = new Long[]{34L, 2L, 1L};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inNotes("1 34 2")));
  }

  @Test
  void inNotes_negative() {
    List<Long> exp = Collections.emptyList();
    assertEquals(exp, Arrays.asList(Task1.inNotes("1 34 -2")));
    assertEquals(exp, Arrays.asList(Task1.inNotes("2 a 3")));
    assertEquals(exp, Arrays.asList(Task1.inNotes("")));
    assertEquals(exp, Arrays.asList(Task1.inNotes("1 34,1 2")));
  }

  @Test
  void variants_right() {
    Long[] notes = new Long[]{100L, 50L, 20L};
    assertEquals(6, Task1.variants(notes, 200, notes[0], ""));
  }

  @Test
  void variants_noChange() {
    Long[] notes = new Long[]{100L};
    assertEquals(0, Task1.variants(notes, 110, notes[0], ""));
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
