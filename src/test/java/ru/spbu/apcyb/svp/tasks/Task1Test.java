package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
class Task1Test {
  @Test
  void emptyCoins() {
    List<HashMap<Integer, Integer>> calculatedAns = Task1.findSolutions(90, new ArrayList<>());
    List<HashMap<Integer, Integer>> trueAns = new ArrayList<>();
    Assertions.assertIterableEquals(calculatedAns, trueAns);
  }

//  @Test
//  void incorrectCoins() {
//    boolean exception = false;
//    try {
//      Task1.findSolutions(10, Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
//    } catch (Exception failRun) {
//      exception = true;
//    }
//    Assertions.assertTrue(exception);
//  }

  @Test
  void correctRun() {
    boolean exception = false;
    try {
      Task1.findSolutions(10, Arrays.asList(1, 2, 5));
    } catch (Exception failRun) {
      exception = true;
    }
    Assertions.assertFalse(exception);
  }

  @Test
  void correctAnswer1() {
    List<HashMap<Integer, Integer>> calculatedAns =
        Task1.findSolutions(5, Arrays.asList(2, 3));
    List<HashMap<Integer, Integer>> trueAns = new ArrayList<>();
    HashMap<Integer, Integer> answerOne = new HashMap<>();
    answerOne.put(2, 1);
    answerOne.put(3, 1);
    trueAns.add(answerOne);
    Assertions.assertIterableEquals(calculatedAns, trueAns);
  }

  @Test
  void correctAnswer2() {
    List<HashMap<Integer, Integer>> calculatedAns =
        Task1.findSolutions(4, Arrays.asList(2, 1));
    List<HashMap<Integer, Integer>> trueAns = new ArrayList<>();

    HashMap<Integer, Integer> answerOne = new HashMap<>();
    answerOne.put(1, 0);
    answerOne.put(2, 2);
    trueAns.add(answerOne);
    HashMap<Integer, Integer> answerTwo = new HashMap<>();
    answerTwo.put(1, 2);
    answerTwo.put(2, 1);
    trueAns.add(answerTwo);
    HashMap<Integer, Integer> answerThree = new HashMap<>();
    answerThree.put(1, 4);
    answerThree.put(2, 0);
    trueAns.add(answerThree);

    Assertions.assertIterableEquals(calculatedAns, trueAns);
  }

  @Test
  void correctAnswer3() {
    List<HashMap<Integer, Integer>> calculatedAns =
        Task1.findSolutions(1000, List.of(1));
    List<HashMap<Integer, Integer>> trueAns = new ArrayList<>();
    HashMap<Integer, Integer> answerOne = new HashMap<>();
    answerOne.put(1, 1000);
    trueAns.add(answerOne);
    Assertions.assertIterableEquals(calculatedAns, trueAns);
  }

  @Test
  void correctAnswer4() {
    List<HashMap<Integer, Integer>> calculatedAns =
        Task1.findSolutions(5, List.of(10, 6));
    List<HashMap<Integer, Integer>> trueAns = new ArrayList<>();
    Assertions.assertIterableEquals(calculatedAns, trueAns);
  }
}