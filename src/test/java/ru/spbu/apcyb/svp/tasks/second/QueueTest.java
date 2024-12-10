package ru.spbu.apcyb.svp.tasks.second;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link MyQueue}.
 */
class QueueTest {
  @Test
  void add2QueueTest() {
    var queue = new MyQueue<Integer>();

    queue.add(1);
    queue.add(2);
    queue.add(3);

    assertEquals(3, queue.peekLast());
  }

  @Test
  void getFirstTest() {
    var queue = new MyQueue<Integer>();

    queue.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(1, queue.getFirst());
  }

  @Test
  void isEmptyTest() {
    var queue = new MyQueue<Integer>();
    assertTrue(queue.isEmpty());

    queue.addAll(List.of(1, 2, 300, 4, 5));
    assertFalse(queue.isEmpty());
  }
}
