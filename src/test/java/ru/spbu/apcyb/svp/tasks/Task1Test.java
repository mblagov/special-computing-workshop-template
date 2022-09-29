package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class Task1Test {
  @Test

    void combinations() {
    int[] arr = {100, 50, 10};
    int actual = Task1.combinations(620, 100, "", arr);
    int expected = 49;
    assertEquals(expected, actual);
  }

  @Test

    void combinations2() {
    int[] arr = {100, 50, 20};
    int actual = Task1.combinations(200, 100, "", arr);
    int expected = 6;
    assertEquals(expected, actual);
  }

  @Test
    void combinations3() {
    int[] arr = {3, 2};
    int actual = Task1.combinations(5, 3, "", arr);
    int expected = 1;
    assertEquals(expected, actual);
  }

  @Test

    void combinations4() {
    int[] arr = {2, 1};
    int actual = Task1.combinations(4, 2, "", arr);
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test

    void combinations5() {
    int[] arr = {1, 2};
    int actual = Task1.combinations(4, 2, "", arr);
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test

    void combinations7() {
    int[] arr = {1, 2};
    int actual = Task1.combinations(-1, 2, "", arr);
    int expected = 0;
    assertEquals(expected, actual);

  }

  @Test

    void combinations8() {
    int[] arr = {};
    int actual = Task1.combinations(200, 0, "", arr);
    int expected = 0;
    assertEquals(expected, actual);
  }

  @Test

    void ord() {
    int[] arr = {10, 50, 100};
    int[] actual = Task1.ord(arr);
    int[] expected = {100, 50, 10};
    assertArrayEquals(actual, expected);
  }

  @Test

    void ord2() {
    int[] arr = {10, 50, 200, 100};
    int[] actual = Task1.ord(arr);
    int[] expected = {200, 100, 50, 10};
    assertArrayEquals(actual, expected);
  }

  @Test

  void numIn() {
    String test = "100 20 50";
    int[] expected = {100, 50, 20};
    int[] actual = Task1.nomIn(test);
    assertArrayEquals(actual, expected);
  }

  @Test

    void numIn2() {
    String test = "-100 20 50";
    int[] expected = {};
    int[] actual = Task1.nomIn(test);
    assertArrayEquals(actual, expected);
  }

  @Test

    void numIn3() {
    String test = "a";
    int[] expected = {};
    int[] actual = Task1.nomIn(test);
    assertArrayEquals(actual, expected);
  }

  @Test
    void sumIn() {
    String test = "200";
    int expected = 200;
    int actual = Task1.sumIn(test);
    assertEquals(expected, actual);
  }

  @Test
    void sumIn2() {
    String test = " ";
    int expected = -1;
    int actual = Task1.sumIn(test);
    assertEquals(expected, actual);
  }

  @Test
    void sumIn3() {
    String test = " ";
    int expected = -1;
    int actual = Task1.sumIn(test);
    assertEquals(expected, actual);
  }
}