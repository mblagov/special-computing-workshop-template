package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LinkedListTest {

  @Test
  @DisplayName("addTest")
  void addTest() {

    LinkedList list = new LinkedList();
    list.add(10);
    Assertions.assertArrayEquals(new Integer[]{10}, list.toArray());
  }

  @Test
  @DisplayName("sizeTest")
  void sizeTest() {
    LinkedList list = new LinkedList();
    Assertions.assertEquals(0, list.size());
    list.add("a");
    Assertions.assertEquals(1, list.size());
  }

  @Test
  @DisplayName("isEmptyTest")
  void isEmptyTest() {

    LinkedList list = new LinkedList();
    Assertions.assertTrue(list.isEmpty());
    list.add(new int[]{1});
    Assertions.assertFalse(list.isEmpty());
  }

  @Test
  @DisplayName("removeTest")
  void removeTest() {

    LinkedList list = new LinkedList();
    list.add("str");
    list.remove("str");
    Assertions.assertTrue(list.isEmpty());
    list.add(10);
    list.add(5);
    list.add(1);
    list.remove(Integer.valueOf(5));
    Assertions.assertArrayEquals(new Object[]{10, 1}, list.toArray());
  }

  @Test
  @DisplayName("getTest")
  void getTest() {

    LinkedList list = new LinkedList();
    list.add(10);
    list.add("str");
    list.add(new int[]{3});
    Assertions.assertEquals("str", list.get(1));
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(3));
  }

  @Test
  @DisplayName("addTest2")
  void addTest2() {

    LinkedList list = new LinkedList();
    list.add(0, 10);
    Assertions.assertEquals(10, list.get(0));
    list.add(0, "str");
    Assertions.assertArrayEquals(new Object[]{"str", 10}, list.toArray());
    list.add(1, 5);
    Assertions.assertArrayEquals(new Object[]{"str", 5, 10}, list.toArray());
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.add(4, 6));
  }

  @Test
  @DisplayName("removeTest2")
  void removeTest2() {

    LinkedList list = new LinkedList();
    list.add(10);
    Object element = list.remove(0);
    Assertions.assertTrue(list.isEmpty());
    Assertions.assertEquals("10", element.toString());

    list.add(10);
    list.add("str");
    list.remove(0);
    Assertions.assertEquals("str", list.get(0));
    list.remove(0);
    Assertions.assertTrue(list.isEmpty());
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.remove(0));

    list.add(1);
    list.add(2);
    list.add(3);
    list.remove(2);
    Assertions.assertArrayEquals(new Object[]{1, 2}, list.toArray());

  }


  @Test
  @DisplayName("indexOfTest")
  void indexOfTest() {

    LinkedList list = new LinkedList();
    list.add("str");
    Assertions.assertEquals(0, list.indexOf("str"));
    Assertions.assertEquals(-1, list.indexOf(10));
  }


}
