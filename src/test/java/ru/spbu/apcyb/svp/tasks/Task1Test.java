package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  void getAllCombinationsTest_basic() {
    Assertions.assertEquals(
        "3\n2 2\n2 1 1\n1 1 1 1",
        Task1.getAllCombinations("4 2 1")
    );
    Assertions.assertEquals(
        "3\n2 2\n2 1 1\n1 1 1 1",
        Task1.getAllCombinations("4 1 2")
    );
    Assertions.assertEquals(
        "1\n3 2",
        Task1.getAllCombinations("5 3 2")
    );
    Assertions.assertEquals(
        "0",
        Task1.getAllCombinations("5 10 6")
    );
    Assertions.assertEquals(
        "1\n3000000000",
        Task1.getAllCombinations("3000000000 3000000000")
    );
    Assertions.assertEquals(
        "1\n1 1 1 1 1",
        Task1.getAllCombinations("5 1 1")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_nan() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("- 2 1")
        ).getMessage().contains("Provided value is not a number")
    );
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("asd 2 1")
        ).getMessage().contains("Provided value is not a number")
    );
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("5 a b")
        ).getMessage().contains("Provided value is not a number")
    );
    Assertions.assertTrue(
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Task1.getAllCombinations("3+2 1+1 2+1")
        ).getMessage().contains("Provided value is not a number")
    );
  }

  @Test
  void getAllCombinationsTest_exceptions_np() {
    Assertions.assertTrue(
        Assertions.assertThrows(
            ArithmeticException.class,
            () -> Task1.getAllCombinations("-5 2 1")
        ).getMessage().contains("Provided value is not positive")
    );
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

