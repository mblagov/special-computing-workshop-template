package ru.spbu.apcyb.svp.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ListArrStackTest {
  ListArrStack stack = new ListArrStack();

  @Test
  void testPush() {
    ListArrStack stack = new ListArrStack();
    stack.push(5400);
    assertEquals(5400, stack.peek());
  }

  @Test
  void testPop() {
    ListArrStack stack = new ListArrStack();
    stack.push(6543);
    stack.push(7647);
    assertEquals(7647, (int) stack.pop());
  }

  @Test
  void testPop2() {
    boolean thrown = true;
    try {
      stack.pop();
    } catch (RuntimeException e) {
      thrown = false;
    }
    assertFalse(thrown);
    //assertThrows
  }

  @Test
  void testPeek() {
    ListArrStack stack = new ListArrStack();
    stack.push(5432);
    stack.push(2345);
    assertEquals(2345, (int) stack.peek());
  }

  @Test
  void isEmpty() {
    assertTrue(stack.isEmpty());
  }
}
