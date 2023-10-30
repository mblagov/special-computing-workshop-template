package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedlistTest {

  private Linkedlist<Integer> linkedlist;

  @Before
  public void setUp() {
    linkedlist = new Linkedlist<>();
  }

  @Test
  public void testAdd() {
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.set(1, 3);
    assertEquals(Integer.valueOf(2), linkedlist.get(15));
    assertEquals(16, linkedlist.size());
    assertArrayEquals(new Integer[]{1, 3, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
        linkedlist.toArray());
  }

  @Test
  public void testAddWithIndex() {
    linkedlist.add(0, 1);
    linkedlist.add(1, 2);
    linkedlist.add(1, 3);
    assertEquals(3, linkedlist.size());
    assertArrayEquals(new Integer[]{1, 3, 2}, linkedlist.toArray());
  }

  @Test
  public void testRemove() {
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.remove(0);
    assertEquals(1, linkedlist.size());
    assertArrayEquals(new Integer[]{2}, linkedlist.toArray());
  }

  @Test
  public void testRemoveObject() {
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.remove(Integer.valueOf(1));
    assertEquals(1, linkedlist.size());
    assertArrayEquals(new Integer[]{2}, linkedlist.toArray());
  }

  @Test
  public void testContains() {
    linkedlist.add(1);
    assertTrue(linkedlist.contains(1));
    assertFalse(linkedlist.contains(2));
  }

  @Test
  public void testIsEmpty() {
    assertTrue(linkedlist.isEmpty());
    linkedlist.add(1);
    assertFalse(linkedlist.isEmpty());
  }

  @Test
  public void testClear() {
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.clear();
    assertArrayEquals(new Integer[]{}, linkedlist.toArray());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testOutOfBounds() {
    linkedlist.add(1);
    linkedlist.get(2);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testToArrayWithArg() {
    Integer[] array = new Integer[5];
    linkedlist.toArray(array);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testIterator() {
    linkedlist.iterator();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddAll() {
    Collection<Integer> collection = Arrays.asList(1, 2, 3);
    linkedlist.addAll(collection);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddAllWithIndex() {
    Collection<Integer> collection = Arrays.asList(1, 2, 3);
    linkedlist.addAll(1, collection);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testIndexOf() {
    linkedlist.indexOf(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testLastIndexOf() {
    linkedlist.lastIndexOf(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testListIterator() {
    linkedlist.listIterator();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testListIteratorWithIndex() {
    linkedlist.listIterator(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSubList() {
    linkedlist.subList(0, 1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRetainAll() {
    Collection<Integer> collection = Arrays.asList(1, 2, 3);
    linkedlist.retainAll(collection);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveAll() {
    Collection<Integer> collection = Arrays.asList(1, 2, 3);
    linkedlist.removeAll(collection);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testContainsAll() {
    Collection<Integer> collection = Arrays.asList(1, 2, 3);
    linkedlist.containsAll(collection);
  }
}
