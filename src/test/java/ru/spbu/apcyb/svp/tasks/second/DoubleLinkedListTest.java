package ru.spbu.apcyb.svp.tasks.second;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DoubleLinkedList}.
 */
public class DoubleLinkedListTest {
  @Test
  public void add2EndTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addLast(2);
    list.addFirst(1);
    list.addLast(3);

    assertEquals(list.getLast(), 3);
  }

  @Test
  public void deleteByIndexTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(list.remove(2), 300);
    assertEquals(list.size(), 4);
  }

  @Test
  public void hasByValueTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertTrue(list.contains(300));
  }

  @Test
  public void isEmptyTest() {
    var list = new DoubleLinkedList<Integer>();
    assertTrue(list.isEmpty());

    list.addAll(List.of(1, 2, 300, 4, 5));
    assertFalse(list.isEmpty());
  }

  @Test
  public void getByIndexTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(list.get(2), 300);
  }

  @Test
  public void insertByIndexTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 5));

    list.add(2, 300);

    assertEquals(list.get(2), 300);
  }

  @Test
  public void notImplementedTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 1));

    assertThrows(UnsupportedOperationException.class, list::descendingIterator);
    assertThrows(UnsupportedOperationException.class, () -> list.removeFirstOccurrence(1));
    assertThrows(UnsupportedOperationException.class, () -> list.removeLastOccurrence(1));

    assertEquals(list.getFirst(), 1);
    assertEquals(list.getLast(), 1);
  }

  @Test
  public void CRUDTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 4, 1));
    list.offerFirst(-10);
    list.offerLast(10);

    list.set(2, 300);

    list.remove(1);
    assertEquals(list.get(1), 300);

    assertEquals(list.getLast(), 10);
    list.removeLast();
    assertEquals(list.peekLast(), 1);

    assertEquals(list.element(), -10);

    list.clear();
    //noinspection ConstantValue
    assertNull(list.peek());

    list.add(100);
    assertEquals(list.peekFirst(), 100);
  }

  @Test
  public void listIteratorTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 3, 4, 5));

    int i = 2;
    var it = list.listIterator(1);

    while (it.hasNext()) {
      assertEquals(it.next(), i++);
    }
  }
}
