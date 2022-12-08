package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 2.
 */
class Task2Test {
  
  @Test
  void testAdd1() {
    DoubleLinkedList list = new DoubleLinkedList();
    
    list.add(1);
    list.add(2);
    list.add(3);
    
    assertEquals("1, 2, 3", list.toString());
  }
  
  @Test
  void testAdd2() {
    DoubleLinkedList list = new DoubleLinkedList();
    
    list.add(0);
    list.add(2);
    list.add(3);
    list.add(1, 1);
    
    assertEquals("0, 1, 2, 3", list.toString());
  }
  
  @Test
  void testRemove() {
    DoubleLinkedList list = new DoubleLinkedList();
    
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    
    assertEquals(5, list.removeLast());
    assertEquals(3, list.remove(2));
    assertEquals(1, list.removeFirst());
  }
  
  @Test
  void testContains() {
    DoubleLinkedList list = new DoubleLinkedList();
    
    list.add(1);
    list.add(2);
    list.add(3);
    
    assertTrue(list.contains(2));
  }
  
  @Test
  void testLLEmptiness() {
    DoubleLinkedList list = new DoubleLinkedList();
    
    list.add(1);
    list.add(2);
    list.add(3);
    
    assertFalse(list.isEmpty());
    
    list.remove(2);
    list.remove(1);
    list.removeFirst();
    
    assertTrue(list.isEmpty());
  }
  
  @Test
  void testGet() {
    DoubleLinkedList list = new DoubleLinkedList();
    
    list.add(1);
    list.add(2);
    list.add(3);
    
    assertEquals(2, list.get(1));
  }
  
  @Test
  void testAllException1() throws UnsupportedOperationException {
    
    DoubleLinkedList list = new DoubleLinkedList();
    Object data = new Object();
    DoubleLinkedList h = new DoubleLinkedList();
    Object[] a = new Object[10];
  
    UnsupportedOperationException thrown0 = assertThrows(UnsupportedOperationException.class, list::size);
  
    assertEquals("java.lang.UnsupportedOperationException", thrown0.toString());
    
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
      list.addAll(h);
    } catch (UnsupportedOperationException thrown) {
      assertNotEquals("", thrown.toString());
    }
    
    try {
      list.addAll(0, h);
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
      list.retainAll(h);
    } catch (UnsupportedOperationException thrown) {
      assertNotEquals("", thrown.toString());
    }
    
    try {
      list.removeAll(h);
    } catch (UnsupportedOperationException thrown) {
      assertNotEquals("", thrown.toString());
    }
    
    try {
      list.containsAll(h);
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
    MyQueue queue = new MyQueue ();
    
    queue.add(1);
    queue.add(2);
    queue.add(3);
    
    assertEquals("1, 2, 3", queue.toString());
  }
  
  @Test
  void testPeek() {
    MyQueue  queue = new MyQueue ();
    
    queue.add(1);
    queue.add(2);
    queue.add(3);
    
    assertEquals(1, queue.peek());
  }
  
  @Test
  void testQLEmptiness() {
    MyQueue  queue = new MyQueue ();
    
    queue.add(1);
    queue.add(2);
    queue.add(3);
    
    assertFalse(queue.isEmpty());
  }
  
  @Test
  void testAllException2() throws UnsupportedOperationException {
    
    MyQueue  queue = new MyQueue ();
    Object data = new Object();
    MyQueue  h = new MyQueue ();
    Object[] a = new Object[10];
  
  
    UnsupportedOperationException thrown0 = assertThrows(UnsupportedOperationException.class, queue::size);
  
    assertEquals("java.lang.UnsupportedOperationException", thrown0.toString());
    
    try {
      queue.add(1);
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
      queue.addAll(h);
    } catch (UnsupportedOperationException thrown) {
      assertNotEquals("", thrown.toString());
    }
    
    try {
      queue.retainAll(h);
    } catch (UnsupportedOperationException thrown) {
      assertNotEquals("", thrown.toString());
    }
    
    try {
      queue.removeAll(h);
    } catch (UnsupportedOperationException thrown) {
      assertNotEquals("", thrown.toString());
    }
    
    try {
      queue.containsAll(h);
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