package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 2.
 */
class Task2Test {

  @Test
  void testAdd1() {
      LinkedList list = new LinkedList();
      list.add(1111);
      list.add(222);
      list.add(3);
      assertEquals("1111, 222, 3", list.toString());
    }

    @Test
    void testAdd2() {
      LinkedList list = new LinkedList();
      list.add(1111);
      list.add(222);
      list.add(3);
      list.add(1, 456);
      assertEquals("1111, 456, 222, 3", list.toString());
    }

    @Test
    void testRemove() {
      LinkedList list = new LinkedList();
      list.add(1111);
      list.add(222);
      list.add("rytp");
      list.add(4444);
      list.add(3);
      assertEquals(3, list.remove(4));
      assertEquals("rytp", list.remove(2));
      assertEquals(1111, list.remove(0));
    }

    @Test
    void testContains() {
      LinkedList list = new LinkedList();
      list.add(1111);
      list.add(222);
      list.add(3);
      assertTrue(list.contains(222));
    }

    @Test
    void testLLEmptiness() {
      LinkedList list = new LinkedList();
      list.add(1111);
      list.add(222);
      list.add(3);
      assertFalse(list.isEmpty());
      list.remove(0);
      list.remove(0);
      list.remove(0);
      assertTrue(list.isEmpty());
    }

    @Test
    void testGet() {
      LinkedList list = new LinkedList();
      list.add(1111);
      list.add(222);
      list.add(3);
      assertEquals(222, list.get(1));
    }

    @Test
    void testLLException() throws UnsupportedOperationException {
      LinkedList list = new LinkedList();
      Object data = new Object();
      LinkedList c = new LinkedList();
      Object[] a = new Object[10];
      try {
        list.size();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.remove(data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.iterator();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.toArray();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.addAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.addAll(0, c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.clear();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.set(0, data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.indexOf(data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.lastIndexOf(data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.listIterator();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.listIterator(0);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.subList(0, 2);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.retainAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.removeAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.containsAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        list.toArray(a);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
    }

    @Test
    void testAdd() {
      QueueList queue = new QueueList();
      queue.add(1111);
      queue.add(222);
      queue.add(3);
      assertEquals("1111, 222, 3", queue.toString());
    }

    @Test
    void testPeek() {
      QueueList queue = new QueueList();
      queue.add(1111);
      queue.add(222);
      queue.add(3);
      assertEquals(1111, queue.peek());
    }

    @Test
    void testQLEmptiness() {
      QueueList queue = new QueueList();
      queue.add(1111);
      queue.add(222);
      queue.add(3);
      assertFalse(queue.isEmpty());
    }

    @Test
    void testQLException() throws UnsupportedOperationException {
      QueueList queue = new QueueList();
      Object data = new Object();
      QueueList c = new QueueList();
      Object[] a = new Object[10];
      try {
        queue.size();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.clear();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.contains(data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.iterator();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.toArray();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.toArray(a);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.remove(data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.addAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.retainAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.removeAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.containsAll(c);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.offer(data);
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.remove();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.poll();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
      try {
        queue.element();
      } catch (UnsupportedOperationException thrown) {
        assertNotEquals("", thrown.toString());
      }
    }
}


