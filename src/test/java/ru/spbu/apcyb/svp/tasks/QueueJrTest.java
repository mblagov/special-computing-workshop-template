package ru.spbu.apcyb.svp.tasks;


import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueueJrTest {


  @Test
  @DisplayName("addTest")
  void addTest() {

    QueueJr queue = new QueueJr();
    queue.add("str");
    Assertions.assertEquals("str", queue.peek());
  }

  @Test
  @DisplayName("sizeTest")
  void sizeTest() {

    QueueJr queue = new QueueJr();
    Assertions.assertEquals(0, queue.size());
    queue.add(5);
    Assertions.assertEquals(1, queue.size());
  }

  @Test
  @DisplayName("pollTest")
  void pollTest() {

    QueueJr queue = new QueueJr();
    Assertions.assertNull(queue.poll());
    queue.add("str");
    queue.add(5);
    Object element = queue.poll();
    Assertions.assertEquals("str", element);
    Assertions.assertArrayEquals(new Object[]{5}, queue.toArray());
  }

  @Test
  @DisplayName("elementTest")
  void elementTest() {

    QueueJr queue = new QueueJr();
    Assertions.assertThrows(NoSuchElementException.class, () -> queue.element());
    queue.add("str");
    queue.add(5);
    Object element = queue.element();
    Assertions.assertEquals("str", element);
    Assertions.assertArrayEquals(new Object[]{"str", 5}, queue.toArray());
  }

  @Test
  @DisplayName("removeTest")
  void removeTest() {

    QueueJr queueJr = new QueueJr();
    Assertions.assertThrows(NoSuchElementException.class, () -> queueJr.remove());
    queueJr.add("str");
    queueJr.add(5);
    Object element = queueJr.remove();
    Assertions.assertEquals("str", element);
    Assertions.assertArrayEquals(new Object[]{5}, queueJr.toArray());
  }

  @Test
  @DisplayName("peekTest")
  void peekTest() {
    QueueJr queueJr = new QueueJr();
    Assertions.assertNull(queueJr.peek());
    queueJr.add("str");
    queueJr.add(5);
    Object element = queueJr.peek();
    Assertions.assertEquals("str", element);
    Assertions.assertArrayEquals(new Object[]{"str", 5}, queueJr.toArray());


  }


}
