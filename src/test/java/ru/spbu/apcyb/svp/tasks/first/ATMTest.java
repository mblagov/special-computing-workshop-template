package ru.spbu.apcyb.svp.tasks.first;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * ATM test class.
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class ATMTest {
  @Test
  public void test5_2_3() {
    var sut = new AutomatedTellerMachine(new int[]{2, 3});
    int amount = 5;

    List<List<Integer>> combinations = sut.getCombinations(amount);

    for (var combination : combinations) {
      assertEquals(amount, combination.stream().mapToInt(Integer::intValue).sum());
    }
  }

  @Test
  public void test4_2_1() {
    var sut = new AutomatedTellerMachine(new int[]{2, 1});
    int amount = 4;

    List<List<Integer>> combinations = sut.getCombinations(amount);

    for (var combination : combinations) {
      assertEquals(amount, combination.stream().mapToInt(Integer::intValue).sum());
    }
  }
}
