package ru.spbu.apcyb.svp.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ListArrTest {
  ListArr list = new ListArr();
  ListArr list2 = new ListArr();

  @Test
  void containsAll() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.containsAll(list2));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void addAll() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.addAll(list2));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void addAll2() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.addAll(0, list2));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void testAddAll()throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.addAll(list2));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void removeAll() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.removeAll(list2));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void retainAll() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.retainAll(list2));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void clear() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.clear());
    assertEquals("", thrown.getMessage());
  }

  @Test
  void set() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.set(0, null));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void indexOf() throws UnsupportedOperationException {
    ListArr list = new ListArr();
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.indexOf(null));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void lastIndexOf() throws UnsupportedOperationException {
    ListArr list = new ListArr();
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf(null));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void listIterator() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, list::listIterator);
    assertEquals("", thrown.getMessage());
  }

  @Test
  void testListIterator() throws UnsupportedOperationException {
    ListArr list = new ListArr();
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void testSubList() throws UnsupportedOperationException {
    ListArr list = new ListArr();
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 1));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void testToArray() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> {
          Object[] a = new Object[0];
          list.toArray(a);
        });
    assertEquals("", thrown.getMessage());
  }

  @Test
  void testToArray2() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, list::toArray);
    assertEquals("", thrown.getMessage());
  }

  @Test
  void testIterator() throws UnsupportedOperationException {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, list::iterator);
    assertEquals("", thrown.getMessage());
  }

  @Test
  void addIndexTest2() {
    IndexOutOfBoundsException thrown =
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 9));
    assertEquals("Некуда добавлять или неправильный индекс", thrown.getMessage());
  }

  @Test
  void removeTest2() {
    IndexOutOfBoundsException thrown =
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertEquals("Некуда добавлять или неправильный индекс", thrown.getMessage());
  }

  @Test
  void removeTest3() {
    UnsupportedOperationException thrown =
        assertThrows(UnsupportedOperationException.class, () -> list.remove(null));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void AddIndexTest() {
    ListArr stack = new ListArr();
    stack.stackPush(0);
    stack.add(0);
    stack.add(1);
    stack.add(2);
    stack.add(3);
    stack.add(4);
    stack.add(5);
    stack.add(6);
    stack.add(7);
    stack.add(8);
    stack.add(2, 654);
    assertEquals(654, stack.get(2));
  }


  @Test
  void PeekTest() {
    ListArr stack = new ListArr();
    stack.stackPush(6789);
    stack.add(1);
    stack.add(2);
    stack.add(3);
    assertEquals(6789, stack.stackPeek());
  }

  @Test
  void containsTest() {
    ListArr stack = new ListArr();
    stack.add(1);
    stack.add(2);
    stack.add(null);
    stack.add(3);
    assertTrue(stack.contains(3));
  }

  @Test
  void isEmptyTest() {
    ListArr stack = new ListArr();
    stack.add(1);
    assertFalse(stack.isEmpty());
  }

  @Test
  void removeFirstTest() {
    ListArr stack = new ListArr();
    stack.add(2);
    stack.add(1);
    stack.add(null);
    stack.add(2);
    stack.add(3);
    stack.stackPop();
    assertEquals(1, stack.stackPeek());
  }

  @Test
  void removeTest() {
    ListArr stack = new ListArr();
    stack.add(2);
    stack.add(1);
    stack.add(null);
    stack.add(2);
    stack.add(3);
    stack.remove(0);
    assertEquals(1, stack.stackPeek());
  }


  @Test
  void sizeTest() {
    ListArr stack = new ListArr();
    stack.add(2);
    stack.add(1);
    stack.add(null);
    stack.add(2);
    stack.add(3);
    stack.add(2);
    stack.add(1);
    stack.add(null);
    stack.add(2);
    stack.add(3);
    stack.add(2);
    stack.add(1);
    stack.add(null);
    stack.add(2);
    stack.add(3);
    stack.remove(0);
    assertEquals(14, stack.size());
  }
}
