package ru.spbu.apcyb.svp.tasks.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task1_Test {

  @Test
  void NormalSumTest() {
    System.setIn(new ByteArrayInputStream("5\n".getBytes()));
    assertEquals(5L, Task1.getSum());
  }

  @Test
  void ZeroSumTest() {
    System.setIn(new ByteArrayInputStream("0\n".getBytes()));
    ArithmeticException ex = assertThrows(ArithmeticException.class, Task1::getSum);
    assertEquals("The sum is less than zero or equal to zero!!!", ex.getMessage());
  }

  @Test
  void NegativeSumTest() {
    System.setIn(new ByteArrayInputStream("-5\n".getBytes()));
    ArithmeticException ex = assertThrows(ArithmeticException.class, Task1::getSum);
    assertEquals("The sum is less than zero or equal to zero!!!", ex.getMessage());
  }

  @Test
  void EmptySumTest() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    NoSuchElementException ex = assertThrows(NoSuchElementException.class, Task1::getSum);
    assertEquals("The sum has not been input", ex.getMessage());
  }

  @Test
  void NotNumberSumTest() {
    System.setIn(new ByteArrayInputStream("qwerty".getBytes()));
    NoSuchElementException ex = assertThrows(NoSuchElementException.class, Task1::getSum);
    assertEquals("Not only numbers were put in", ex.getMessage());
  }

  @Test
  void NormalBanknotesTest() {
    System.setIn(new ByteArrayInputStream("5 7\n".getBytes()));
    assertEquals(Arrays.asList(7L, 5L), Task1.getBanknotes());
  }

  @Test
  void NoNomberBanknotesTest() {
    System.setIn(new ByteArrayInputStream("\n".getBytes()));
    NullPointerException ex = assertThrows(NullPointerException.class, Task1::getBanknotes);
    assertEquals("The banknotes has not been input", ex.getMessage());
  }
  @Test
  void EmptyBanknotesTest() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    NoSuchElementException ex = assertThrows(NoSuchElementException.class, Task1::getBanknotes);
    assertEquals("The banknotes has not been input", ex.getMessage());
  }
  @Test
  void Test_Sum4_Banknotes2_1() {
    List<List<Long>> expected = new ArrayList<>();
    expected.add(Arrays.asList(2L, 2L));
    expected.add(Arrays.asList(2L, 1L, 1L));
    expected.add(Arrays.asList(1L, 1L, 1L, 1L));
    assertEquals(expected, Task1.wrappingfindCombinations(4L, Arrays.asList(2L, 1L)));
  }
  @Test
  void Test_Sum5_Banknotes3_2() {
    List<List<Long>> expected = new ArrayList<>();
    expected.add(Arrays.asList(3L, 2L));
    assertEquals(expected, Task1.wrappingfindCombinations(5L, Arrays.asList(3L, 2L)));
  }
  @Test
  void Test_Sum1000_Banknotes1_forCountOfCombination() {
    List<List<Long>> expected = Task1.wrappingfindCombinations(1000L, Arrays.asList(1L));
    Assertions.assertEquals(1, expected.size());
  }

  @Test
  void Test_Sum1000_Banknotes1_500_forCountOfCombination_andCombination() {
    List<List<Long>> result = Task1.wrappingfindCombinations(1000, Arrays.asList(500L, 1L));
    Assertions.assertNotEquals(0, result.size());
  }

  @Test
  void Test_Sum5_Banknotes10_6_forCountOfCombination() {
    List<List<Long>> result = Task1.wrappingfindCombinations(5, Arrays.asList(10L, 6L));
    Assertions.assertEquals(0, result.size());
  }
}