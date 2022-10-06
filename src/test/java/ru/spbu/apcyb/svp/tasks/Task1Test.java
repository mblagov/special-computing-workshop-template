package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  void getAllCombinationsTest_basic_1() {
    Assertions.assertEquals(
        "3\n2 2\n2 1 1\n1 1 1 1",
        Task1.getAllCombinations("4 2 1")
    );
  }
  @Test
  void getAllCombinationsTest_basic_2() {
    Assertions.assertEquals(
        "3\n2 2\n2 1 1\n1 1 1 1",
        Task1.getAllCombinations("4 1 2")
    );
  }

  @Test
  void getAllCombinationsTest_basic_3() {
    Assertions.assertEquals(
        "1\n3 2",
        Task1.getAllCombinations("5 3 2")
    );
  }

  @Test
  void getAllCombinationsTest_basic_4() {
    Assertions.assertEquals(
        "0",
        Task1.getAllCombinations("5 10 6")
    );
  }

  @Test
  void getAllCombinationsTest_basic_5() {
    Assertions.assertEquals(
        "1\n3000000000",
        Task1.getAllCombinations("3000000000 3000000000")
    );
  }

  @Test
  void getAllCombinationsTest_basic_6() {
    Assertions.assertEquals(
        "1\n1 1 1 1 1",
        Task1.getAllCombinations("5 1 1")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_nan_1() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("- 2 1")
        ).getMessage().contains("Provided value is not a number")
    );
  }
  @Test
  void getAllCombinationsTest_exceptions_nan_2() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("asd 2 1")
        ).getMessage().contains("Provided value is not a number")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_nan_3() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("5 a b")
        ).getMessage().contains("Provided value is not a number")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_nan_4() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("3+2 1+1 2+1")
        ).getMessage().contains("Provided value is not a number")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_np_1() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            ArithmeticException.class,
            () -> Task1.getAllCombinations("-5 2 1")
        ).getMessage().contains("Provided value is not positive")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_np_2() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            ArithmeticException.class,
            () -> Task1.getAllCombinations("5 -1 1")
        ).getMessage().contains("Provided value is not positive")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_nev() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("10")
        ).getMessage().contains("Not enough values")
    );
  }
}

