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
  
    UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, list::size);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.remove(data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
    
    thrown = assertThrows(UnsupportedOperationException.class, list::iterator);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
    
    thrown = assertThrows(UnsupportedOperationException.class, list::toArray);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.addAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, list::clear);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.set(0, data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.indexOf(data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf(data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, list::listIterator);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 2));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.retainAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.removeAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.containsAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.containsAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> list.toArray(a));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
    
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
  
  
    UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, queue::size);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, queue::clear);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.contains(data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, queue::iterator);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, queue::toArray);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.remove(data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.addAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.retainAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.removeAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.containsAll(h));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, () -> queue.offer(data));
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, queue::remove);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
  
    thrown = assertThrows(UnsupportedOperationException.class, queue::poll);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
    
    thrown = assertThrows(UnsupportedOperationException.class, queue::element);
    assertEquals("java.lang.UnsupportedOperationException", thrown.toString());
    
  }
}