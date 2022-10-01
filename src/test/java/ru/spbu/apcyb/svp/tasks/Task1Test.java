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
    int[] arr2 = {100, 50, 20};
    int actual2 = Task1.combinations(200, 100, "", arr2);
    int expected2 = 6;
    assertEquals(expected2, actual2);
    int[] arr3 = {3, 2};
    int actual3 = Task1.combinations(5, 3, "", arr3);
    int expected3 = 1;
    assertEquals(expected3, actual3);
    int[] arr4 = {2, 1};
    int actual4 = Task1.combinations(4, 2, "", arr4);
    int expected4 = 3;
    assertEquals(expected4, actual4);
    int[] arr5 = {1, 2};
    int actual5 = Task1.combinations(4, 2, "", arr5);
    int expected5 = 3;
    assertEquals(expected5, actual5);
    int[] arr6 = {1, 2};
    int actual6 = Task1.combinations(-1, 2, "", arr6);
    int expected6 = 0;
    assertEquals(expected6, actual6);
    int[] arr7 = {};
    int actual7 = Task1.combinations(200, 0, "", arr7);
    int expected7 = 0;
    assertEquals(expected7, actual7);

  }

  @Test
  void ord() {
    int[] arr = {10, 50, 100};
    int[] actual = Task1.ord(arr);
    int[] expected = {100, 50, 10};
    assertArrayEquals(actual, expected);
    int[] arr2 = {10, 50, 200, 100};
    int[] actual2 = Task1.ord(arr2);
    int[] expected2 = {200, 100, 50, 10};
    assertArrayEquals(actual2, expected2);
  }

  @Test
  void nomIn() {
    String test = "100 20 50";
    String test2 = "-100 20 50";
    String test3 = "a";
    int[] expected = {100, 50, 20};
    int[] expected2 = {};
    int[] expected3 = {};
    int[] actual = Task1.nomIn(test);
    int[] actual2 = Task1.nomIn(test2);
    int[] actual3 = Task1.nomIn(test3);
    assertArrayEquals(actual, expected);
    assertArrayEquals(actual2, expected2);
    assertArrayEquals(actual3, expected3);
  }

  @Test
  void sumIn() {
    String[] test = {"200", " ", "a", "-1"};
    int[] expected = {200, -1, -1, -1};
    int[] actual = new int[4];
    for (int i = 0; i < 4; i++) {
      actual[i] = Task1.sumIn(test[i]);
      assertEquals(expected[i], actual[i]);
    }
  }
}
