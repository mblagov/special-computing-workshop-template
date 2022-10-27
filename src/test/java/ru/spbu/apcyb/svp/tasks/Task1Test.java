package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  void print() {
    long[] coins = {1};
    long[] use = {0, 2, 3};
    long num = 5;
    boolean thrown = false;
    try {
      Task1.print(coins, use, num);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void enterSum() {
    System.setIn(new ByteArrayInputStream("5".getBytes()));
    long n = Task1.enterSum();
    System.setIn(System.in);
    assertEquals(5, n);
  }

  @Test
  void enterSum1() {
    boolean thrown = true;
    try {
      System.setIn(new ByteArrayInputStream("asadasd".getBytes()));
      System.setIn(System.in);
      Task1.enterSum();
    } catch (Exception e) {
      thrown = false;
    }
    assertFalse(thrown);
  }

  @Test
  void enterAmount1() {
    System.setIn(new ByteArrayInputStream("5".getBytes()));
    long actual = Task1.enterAmount();
    System.setIn(System.in);
    long expected = 5;
    assertEquals(expected, actual);
  }

  @Test
  void enterAmount2() {
    boolean thrown = false;
    try {
      System.setIn(new ByteArrayInputStream("asdfsa".getBytes()));
      Task1.enterAmount();
      System.setIn(System.in);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void enterCoins() {
    System.setIn(new ByteArrayInputStream("3 5 4".getBytes()));
    long[] actual = Task1.enterCoins(3);
    System.setIn(System.in);
    long[] expected = {3, 5, 4};
    assertArrayEquals(expected, actual);
  }

  @Test
  void check1() {
    long n = 6;
    long expected = 6;
    long actual = Task1.check(n);
    assertEquals(expected, actual);
  }

  @Test
  void check2() {
    long n = 1;
    boolean thrown = false;
    try {
      Task1.check(n);
    } catch (Exception e) {
      thrown = true;
    }
    assertFalse(thrown);
  }

  @Test
  void order() {
    long[] arr = {5, 22, 22, 0, -5, 0, 0, 0, 7, 5};
    long[] actual = Task1.order(arr);
    long[] expected = {-5, 0, 5, 7, 22};
    assertArrayEquals(expected, actual);
  }

  @Test
  void order1() {
    long[] arr = { };
    long[] actual = Task1.order(arr);
    long[] expected = { };
    assertArrayEquals(expected, actual);
  }

  @Test
  void deleteUselessElements() {
    long[] coins = {1, 5, 6};
    long sum = 2;
    long[] actual = Task1.deleteUselessElements(coins, sum);
    long[] expected = {1};
    assertArrayEquals(expected, actual);
  }

  @Test
  void deleteUselessElements1() {
    long[] coins = {4, 5, 6};
    long sum = 1;
    long[] actual = Task1.deleteUselessElements(coins, sum);
    long[] expected = {0};
    assertArrayEquals(expected, actual);
  }

  @Test
  void searchOfOptions() {
    long sum = 5;
    long[] coins = {1, 2};
    long[] use = new long[coins.length];
    long actual = Task1.searchOfOptions(sum, sum, coins, use, 0, 0);
    long expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  void searchOfOptions1() {
    boolean thrown = false;
    long sum = 1;
    long[] coins = {0};
    long[] use = new long[coins.length];
    try {
      Task1.searchOfOptions(sum, sum, coins, use, 0, 0);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void searchOfOptions2() {
    long sum = 5;
    long[] coins = {5};
    long[] use = new long[coins.length];
    long actual = Task1.searchOfOptions(sum, 0, coins, use, 0, 0);
    long expected = 1;
    assertEquals(expected, actual);
  }

  @Test
  void searchOfOptions3() {
    long sum = -4;
    long[] coins = {1};
    long[] use = new long[coins.length];
    long actual = Task1.searchOfOptions(sum, sum, coins, use, 0, 0);
    long expected = 0;
    assertEquals(expected, actual);
  }

  @Test
  void searchOfOptions4() {
    boolean thrown = false;
    long sum = 5;
    long[] coins = {1};
    long[] use = new long[coins.length];
    try {
      Task1.searchOfOptions(sum, sum, coins, use, 3, 0);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void searchOfOptions5() {
    long sum = 5;
    long[] coins = {1};
    long[] use = {0, 1, 2};
    boolean thrown = false;
    try {
      Task1.searchOfOptions(sum, 5, coins, use, 0, 0);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void searchOfOptions6() {
    long sum = 5;
    long[] coins = {1, 2, 3};
    long[] use = {0};
    boolean thrown = false;
    try {
      Task1.searchOfOptions(sum, 5, coins, use, 0, 0);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}
