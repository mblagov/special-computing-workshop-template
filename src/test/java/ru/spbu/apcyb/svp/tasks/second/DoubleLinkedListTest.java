package ru.spbu.apcyb.svp.tasks.second;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link DoubleLinkedList}.
 */
public class DoubleLinkedListTest {
  @Test
  public void add2EndTest() {
    var list = new DoubleLinkedList<Integer>();

    list.addLast(1);
    list.addLast(2);
    list.addLast(3);

    assertEquals(list.getLast(), 3);
  }

  @Test
  public void deleteByIndex() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(list.remove(2), 300);
    assertEquals(list.size(), 4);
  }

  @Test
  public void hasByValue() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertTrue(list.contains(300));
  }

  @Test
  public void emptyTest() {
    var list = new DoubleLinkedList<Integer>();
    assertTrue(list.isEmpty());

    list.addAll(List.of(1, 2, 300, 4, 5));
    assertFalse(list.isEmpty());
  }

  @Test
  public void getByIndex() {
    var list = new DoubleLinkedList<Integer>();

    list.addAll(List.of(1, 2, 300, 4, 5));

    assertEquals(list.get(2), 300);
  }

  @Test
  public void insertByIndex() {
    var list = new DoubleLinkedList<Integer>();
    list.addAll(List.of(1, 2, 4, 5));

    list.add(2, 300);

    assertEquals(list.get(2), 300);
  }
}
