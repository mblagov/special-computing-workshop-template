package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ArrayListTest {

  ArrayList list = new ArrayList();
  ArrayList list2 = new ArrayList();

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
    ArrayList list = new ArrayList();
    UnsupportedOperationException thrown =
            assertThrows(UnsupportedOperationException.class, () -> list.indexOf(null));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void lastIndexOf() throws UnsupportedOperationException {
    ArrayList list = new ArrayList();
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
    ArrayList list = new ArrayList();
    UnsupportedOperationException thrown =
            assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    assertEquals("", thrown.getMessage());
  }

  @Test
    void testSubList() throws UnsupportedOperationException {
    ArrayList list = new ArrayList();
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
    assertEquals("Некуда добавлять или неправильный индекс.", thrown.getMessage());
  }

  @Test
  void removeTest2() {
    IndexOutOfBoundsException thrown =
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertEquals("Некуда добавлять или неправильный индекс.", thrown.getMessage());
  }

  @Test
  void removeTest3() {
    UnsupportedOperationException thrown =
            assertThrows(UnsupportedOperationException.class, () -> list.remove(null));
    assertEquals("", thrown.getMessage());
  }

  @Test
  void addIndexTest() {
    ArrayList list = new ArrayList();
    list.addFirst(0);
    list.add(0);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    list.add(7);
    list.add(8);
    list.add(2, 4500);
    assertEquals(4500, list.get(2));
  }


  @Test
  void getFirstTest() {
    ArrayList list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.addFirst(2323);
    assertEquals(2323, list.getFirst());
  }

  @Test
  void containsTest() {
    ArrayList list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(null);
    list.add(3);
    assertTrue(list.contains(3));
  }

  @Test
  void isEmptyTest() {
    ArrayList list = new ArrayList();
    list.add(1);
    assertFalse(list.isEmpty());
  }

  @Test
  void removeFirstTest() {
    ArrayList list = new ArrayList();
    list.add(2);
    list.add(1);
    list.add(null);
    list.add(2);
    list.add(3);
    list.removeFirst();
    assertEquals(1, list.getFirst());
  }

  @Test
  void removeTest() {
    ArrayList list = new ArrayList();
    list.add(2);
    list.add(1);
    list.add(null);
    list.add(2);
    list.add(3);
    list.remove(0);
    assertEquals(1, list.getFirst());
  }


  @Test
  void sizeTest() {
    ArrayList list = new ArrayList();
    list.add(2);
    list.add(1);
    list.add(null);
    list.add(2);
    list.add(3);
    list.add(2);
    list.add(1);
    list.add(null);
    list.add(2);
    list.add(3);
    list.add(2);
    list.add(1);
    list.add(null);
    list.add(2);
    list.add(3);
    list.remove(0);
    assertEquals(14, list.size());
  }

}