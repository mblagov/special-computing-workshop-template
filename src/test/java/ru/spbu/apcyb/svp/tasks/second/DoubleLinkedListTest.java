package ru.spbu.apcyb.svp.tasks.second;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DoubleLinkedList}.
 */
class DoubleLinkedListTest {
  @Test
  void add2EndTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addLast(2);
    list.addFirst(1);
    list.addLast(3);

    assertEquals(3, list.getLast());
  }

  @Test
  void deleteByIndexTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(300, list.remove(2));
    assertEquals(4, list.size());
  }

  @Test
  void hasByValueTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertTrue(list.contains(300));
  }

  @Test
  void isEmptyTest() {
    var list = new DoubleLinkedList<Integer>();
    assertTrue(list.isEmpty());

    list.addAll(List.of(1, 2, 300, 4, 5));
    assertFalse(list.isEmpty());
  }

  @Test
  void getByIndexTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(300, list.get(2));
  }

  @Test
  void insertByIndexTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 5));

    list.add(2, 300);

    assertEquals(300, list.get(2));
  }

  @Test
  void notImplementedTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 1));

    assertThrows(UnsupportedOperationException.class, list::descendingIterator);
    assertThrows(UnsupportedOperationException.class, () -> list.removeFirstOccurrence(1));
    assertThrows(UnsupportedOperationException.class, () -> list.removeLastOccurrence(1));

    assertEquals(1, list.getFirst());
    assertEquals(1, list.getLast());
  }

  @Test
  void AddTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 1));

    list.offerFirst(-10); // -10, 1, 2, 4, 1
    assertEquals(-10, list.getFirst());

    // offer is equivalent offerLast
    list.offer(5); // -10, 1, 2, 4, 1, 5
    assertEquals(5, list.getLast());

    list.offerLast(10); // -10, 1, 2, 4, 1, 5, 10
    assertEquals(10, list.getLast());
  }

  @Test
  void SetTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 1));

    list.set(2, 300); // 1, 2, 300, 1
    assertEquals(300, list.get(2));

    list.set(0, 100); // 100, 2, 300, 1
    assertEquals(100, list.getFirst());

    list.set(3, 500); // 100, 2, 300, 500
    assertEquals(500, list.getLast());
  }

  @Test
  void RemoveTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 1));

    list.remove(1); // 1, 4, 1

    assertEquals(1, list.getFirst());
    assertEquals(4, list.get(1));

    list.removeLast(); // 1, 4
    assertEquals(4, list.peekLast()); // peekLast doesn't throw exception if no element

    list.removeFirst(); // 4
    assertEquals(4, list.peekFirst());

    list.removeLast(); // nothing left
    assertNull(list.peek());
  }

  @Test
  void ClearTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 1));

    list.clear();
    //noinspection ConstantValue
    assertNull(list.peek());

    list.add(100);
    assertEquals(100, list.peekFirst());
  }

  @Test
  void listIteratorTest() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 3, 4, 5));

    int i = 2;
    var it = list.listIterator(1);

    while (it.hasNext()) {
      assertEquals(i++, it.next());
    }
  }
}
