package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 2.
 */
class Task2Test {

  @Test
  void testDllAddEl() {
    DoubleLinkedList list = new DoubleLinkedList();
    list.add(1);
    Object[] l = list.toArray();
    Object[] arr = {1};
    assertArrayEquals(arr, l);
  }

  @Test
  void testDllDelIndex() {
    DoubleLinkedList list = new DoubleLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.remove(1);
    assertArrayEquals(new Object[]{1, 3, 4}, list.toArray());
    list.remove(2);
    assertArrayEquals(new Object[]{1, 3}, list.toArray());
  }

  @Test
  void testDllAddIndex() {
    DoubleLinkedList list = new DoubleLinkedList();
    list.add(3);
    list.add(1, 1);
    list.add(2, 2);
    Object[] l = list.toArray();
    Object[] arr = {3,1,2};
    assertArrayEquals(arr, l);
  }

  @Test
  void testDllFindEl() {
    DoubleLinkedList list = new DoubleLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    Object[] arr = {1,2,3};
    assertArrayEquals(list.toArray(), arr);
    assertTrue(list.contains(3));
  }

  @Test
  void testDllEmpty() {
    DoubleLinkedList list = new DoubleLinkedList();
    assertTrue(list.isEmpty());
    list.add(1);
    assertFalse(list.isEmpty());
  }

  @Test
  void testDllIndex() {
    DoubleLinkedList list = new DoubleLinkedList();
    list.add(1);
    list.add(2);
    assertEquals(2, list.get(1));
  }

  @Test
  void testQAdd() {
    Queue2 queue = new Queue2();
    queue.add(1);
    queue.add(2);
    assertArrayEquals(new Object[]{1,2}, queue.toArray());
  }

  @Test
  void testQPeek() {
    Queue2 queue = new Queue2();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    assertEquals(1, queue.peek());
  }

  @Test
  void testQEmpty() {
    Queue2 queue = new Queue2();
    queue.add(1);
    queue.add(2);
    assertFalse(queue.isEmpty());
  }
}
