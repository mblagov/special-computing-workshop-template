package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Тесты для задания 1.
 */
public class Task1Test {

  @Test
  void getAllCombinationsTest1() {
    ArrayList<ArrayList<Integer>> expected = new ArrayList<>(
        Arrays.asList(
            new ArrayList<>(Arrays.asList(2, 2)),
            new ArrayList<>(Arrays.asList(1, 1, 2)),
            new ArrayList<>(Arrays.asList(1, 1, 1, 1))
        )
    );
    ArrayList<ArrayList<Integer>> actual = (ArrayList<ArrayList<Integer>>) Task1.getAllCombinations(
        new int[]{4, 2, 1});
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void getAllCombinationsTest2() {
    ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
    ArrayList<ArrayList<Integer>> actual = (ArrayList<ArrayList<Integer>>) Task1.getAllCombinations(
        new int[]{0, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void getAllCombinationsTest3() {
    ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
    ArrayList<ArrayList<Integer>> actual = (ArrayList<ArrayList<Integer>>) Task1.getAllCombinations(
        new int[]{4, 9, 8, 7, 6, 5});
    Assertions.assertEquals(expected, actual);
  }
}

