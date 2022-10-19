package ru.spbu.apcyb.svp.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.Task2.LinkedList;
import ru.spbu.apcyb.svp.tasks.Task2.Stack;

/**
 * Тесты для задания 2.
 */
class Task2Test {

  // LinkedList Tests:

  @Test
  void addsElementsToTheEndCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    Assertions.assertEquals("[1, 2, 3]", a.toString());
  }

  @Test
  void addsElementsToTheEndCorrectly2() {
    Assertions.assertThrowsExactly(NullPointerException.class, () -> {
      LinkedList a = new LinkedList();
      a.add(null);
    });
  }

  @Test
  void addsElementToSpecifiedPositionCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(0, 4);
    Assertions.assertEquals("[4, 1, 2, 3]", a.toString());
  }

  @Test
  void addsElementToSpecifiedPositionCorrectly2() {
    Assertions.assertThrowsExactly(NullPointerException.class, () -> {
      LinkedList a = new LinkedList();
      a.add(1);
      a.add(2);
      a.add(0, null);
    });
  }

  @Test
  void addsElementToSpecifiedPositionCorrectly3() {
    Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> {
      LinkedList a = new LinkedList();
      a.add(1);
      a.add(2);
      a.add(10, 3);
    });
  }

  @Test
  void getsElementCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    Assertions.assertEquals(1, a.get(0));
  }

  @Test
  void getsElementCorrectly2() {
    Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> {
      LinkedList a = new LinkedList();
      a.add(1);
      a.add(2);
      a.get(10);
    });
  }

  @Test
  void sizeIsCorrect1() {
    LinkedList a = new LinkedList();
    Assertions.assertEquals(0, a.size());
  }

  @Test
  void sizeIsCorrect2() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    Assertions.assertEquals(3, a.size());
  }

  @Test
  void isEmptyWorksCorrectly1() {
    LinkedList a = new LinkedList();
    Assertions.assertTrue(a.isEmpty());
  }

  @Test
  void isEmptyWorksCorrectly2() {
    LinkedList a = new LinkedList();
    a.add(1);
    Assertions.assertFalse(a.isEmpty());
  }

  @Test
  void containsWorksCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    Assertions.assertTrue(a.contains(2));
  }

  @Test
  void containsWorksCorrectly2() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    Assertions.assertFalse(a.contains(4));
  }

  @Test
  void removesSpecificElementCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    a.remove(Integer.valueOf(2));
    Assertions.assertEquals("[1, 3]", a.toString());
  }

  @Test
  void removesSpecificElementCorrectly2() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    Assertions.assertFalse(a.remove(Integer.valueOf(4)));
  }

  @Test
  void removesElementByIndexCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    a.remove(1);
    Assertions.assertEquals("[1, 3]", a.toString());
  }

  @Test
  void removesElementByIndexCorrectly2() {
    Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> {
      LinkedList a = new LinkedList();
      a.add(1);
      a.add(2);
      a.remove(10);
    });
  }

  @Test
  void indexOfWorksCorrectly1() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    Assertions.assertEquals(1, a.indexOf(2));
  }

  @Test
  void indexOfWorksCorrectly2() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    Assertions.assertEquals(-1, a.indexOf(3));
  }

  @Test
  void clearWorksCorrectly() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add(2);
    a.add(3);
    a.clear();
    Assertions.assertEquals("[]", a.toString());
  }

  @Test
  void toStringWorksCorrectly() {
    LinkedList a = new LinkedList();
    a.add(1);
    a.add("two");
    a.add(3L);
    Assertions.assertEquals("[1, two, 3]", a.toString());
  }

  @Test
  void addsManyElements() {
    LinkedList a = new LinkedList();
    for (int i = 0; i < 100; i++) {
      a.add(i);
    }
    Assertions.assertEquals(100, a.getSize());
  }

  @Test
  void unsupportedOperationForContainsAllCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      boolean flag = a.containsAll(Collections.EMPTY_LIST);
    });
  }

  @Test
  void unsupportedOperationForIteratorCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      Iterator<Object> it = a.iterator();
    });
  }

  @Test
  void unsupportedOperationForToArray1Check() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      Object[] arr = a.toArray();
    });
  }

  @Test
  void unsupportedOperationForToArray2Check() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      Object[] b = new Object[] {};
      Object[] arr = a.toArray(b);
    });
  }

  @Test
  void unsupportedOperationForAddAll1Check() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.addAll(Collections.EMPTY_LIST);
    });
  }

  @Test
  void unsupportedOperationForAddAll2Check() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.addAll(0, Collections.EMPTY_LIST);
    });
  }

  @Test
  void unsupportedOperationForRemoveAllCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.removeAll(Collections.EMPTY_LIST);
    });
  }

  @Test
  void unsupportedOperationForRetainAllCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.retainAll(Collections.EMPTY_LIST);
    });
  }

  @Test
  void unsupportedOperationForReplaceALlCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      UnaryOperator<Object> b = o -> null;
      a.replaceAll(b);
    });
  }

  @Test
  void unsupportedOperationForSortCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      Comparator<Object> c = (o1, o2) -> 0;
      a.sort(c);
    });
  }

  @Test
  void unsupportedOperationForSetCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.set(0, 0);
    });
  }

  @Test
  void unsupportedOperationForLastObjectOfCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.lastIndexOf(0);
    });
  }

  @Test
  void unsupportedOperationForListIterator1Check() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.listIterator();
    });
  }

  @Test
  void unsupportedOperationForListIterator2Check() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.listIterator(0);
    });
  }

  @Test
  void unsupportedOperationForSubListCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      a.subList(0, 1);
    });
  }

  @Test
  void unsupportedOperationForSplitIteratorCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      LinkedList a = new LinkedList();
      Spliterator<Object> s = a.spliterator();
    });
  }

  // Stack tests

  @Test
  void pushAndPeekWorkCorrectly() {
    Stack s = new Stack();
    s.push(1);
    Assertions.assertEquals(1, s.peek());
  }

  @Test
  void pushAndPopWorkCorrectly() {
    Stack s = new Stack();
    s.push(1);
    Assertions.assertEquals(1, s.pop());
  }

  @Test
  void popWorksCorrectly() {
    Stack s = new Stack();
    s.push(1);
    s.pop();
    Assertions.assertThrowsExactly(EmptyStackException.class, s::pop);
  }

  @Test
  void peekWorksCorrectly() {
    Stack s = new Stack();
    Assertions.assertThrowsExactly(EmptyStackException.class, s::peek);
  }

  @Test
  void emptyWorksCorrectly() {
    Stack s = new Stack();
    s.push(1);
    s.pop();
    Assertions.assertTrue(s.empty());
  }

  @Test
  void unsupportedOperationForSearchCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      Stack s = new Stack();
      s.search(0);
    });
  }

  @Test
  void unsupportedOperationForEqualsCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      Stack s1 = new Stack();
      Stack s2 = new Stack();
      boolean flag = s1.equals(s2);
    });
  }

  @Test
  void unsupportedOperationForHashCodeCheck() {
    Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> {
      Stack s = new Stack();
      int hash = s.hashCode();
    });
  }
}
