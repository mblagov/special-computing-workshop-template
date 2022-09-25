package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
public class Task1Test {

  @Test
  public void inDenom_1() {
    Long[] exp = new Long[]{34L, 2L, 1L};
    assertEquals(Arrays.asList(exp), Arrays.asList(Task1.inDenom("1 34 2")));
  }

  @Test
  public void variants_1() {
    Long[] denom = new Long[]{100L, 50L, 20L};
    assertEquals(Task1.variants(denom, 200, denom[0], ""), 6);
  }

  @Test
  public void inSum_1(){
    assertEquals(Task1.inSum("15"),15);
  }
}
