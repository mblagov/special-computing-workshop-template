package ru.spbu.apcyb.svp.tasks.task1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task1.SumExchange;
import ru.spbu.apcyb.svp.tasks.task1.Task1;



/**
 * Тесты для задания 1.
 */
class Task1Test {

  private Task1 task1;

  @BeforeEach
  void setUp() {
    task1 = new Task1();
  }

  @Test
  void inputDataTestNormal() {
    String expected = "10 1 2 3";
    System.setIn(new ByteArrayInputStream("10 1 2 3".getBytes()));
    assertEquals(expected, task1.inputData());
  }

  @Test
  void transformInputLineToIntegerListNormal() {
    System.setIn(new ByteArrayInputStream("10 1 2 3".getBytes()));
    List<Integer> listExpected = Arrays.asList(10, 1, 2, 3);
    assertEquals(listExpected, task1.transformInputLineToIntegerList());
  }

  @Test
  void transformInputLineToIntegerListThrownNullPointerException() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    assertThrows(NullPointerException.class, task1::transformInputLineToIntegerList);
  }

  @Test
  void transformInputLineToIntegerListThrownArrayIndexOutOfBoundsException() {
    System.setIn(new ByteArrayInputStream("0".getBytes()));
    assertThrows(ArrayIndexOutOfBoundsException.class, task1::transformInputLineToIntegerList);
  }

  @Test
  void splitAndSortDataNormal() {
    System.setIn(new ByteArrayInputStream("10 1 2 3".getBytes()));
    SumExchange sumExchangeExpected = new SumExchange(10, Arrays.asList(1, 2, 3));
    SumExchange sumExchangeActual = task1.splitAndSortData();
    assertEquals(sumExchangeExpected, sumExchangeActual);
  }

  @Test
  void splitAndSortDataFail() {
    System.setIn(new ByteArrayInputStream("1 0 1 2 3".getBytes()));
    SumExchange sumExchangeExpected = new SumExchange(10, Arrays.asList(1, 2, 3));
    SumExchange sumExchangeActual = task1.splitAndSortData();
    assertNotEquals(sumExchangeExpected, sumExchangeActual);
  }

  @Test
  void splitAndSortDataNormalThrownArithmeticException() {
    System.setIn(new ByteArrayInputStream("-1 1 2 3".getBytes()));
    assertThrows(ArithmeticException.class, task1::splitAndSortData);
  }

}
