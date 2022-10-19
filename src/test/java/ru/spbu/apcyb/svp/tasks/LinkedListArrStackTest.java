package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для стека.
 */
class LinkedListArrStackTest {
  LinkedListArrStack stack = new LinkedListArrStack();

  @Test
  void testPush() {
    LinkedListArrStack stack = new LinkedListArrStack();
    stack.push(1500);
    assertEquals(1500, stack.peek());
  }

  @Test
  void testPop() {
    LinkedListArrStack stack = new LinkedListArrStack();
    stack.push(1200);
    stack.push(1500);
    assertEquals(1500, (int) stack.pop());
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
  }

  @Test
  void testPeek() {
    LinkedListArrStack stack = new LinkedListArrStack();
    stack.push(1200);
    stack.push(1500);
    assertEquals(1500, (int) stack.peek());
  }

  @Test
  void isEmpty() {
    assertTrue(stack.isEmpty());
  }
}
