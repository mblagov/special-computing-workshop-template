package ru.spbu.apcyb.svp.tasks;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Тесты для задания 2. */
class Task2Test {
  private static Stream<Arguments> argsForIsEmpty() {
    return Stream.of(
        Arguments.of(new MyList(), true), Arguments.of(new MyList(List.of(17)), false));
  }

  @ParameterizedTest
  @MethodSource("argsForIsEmpty")
  void isEmpty(MyList list, boolean bool) {
    Assertions.assertEquals(list.isEmpty(), bool);
  }

  private static Stream<Arguments> argsForSize() {
    return Stream.of(
        Arguments.of(new MyList(), 0),
        Arguments.of(new MyList(List.of()), 0),
        Arguments.of(new MyList(List.of(1, 2, 3)), 3),
        Arguments.of(new MyList(List.of(4, 5)), 2),
        Arguments.of(new MyList(List.of(6)), 1));
  }

  @ParameterizedTest
  @MethodSource("argsForSize")
  void size(MyList list, int i) {
    Assertions.assertEquals(list.size(), i);
  }

  private static Stream<Arguments> argsForContains() {
    return Stream.of(
        Arguments.of(new MyList().contains(4), false),
        Arguments.of(new MyList(List.of(1, 2, 3)).contains(1), true),
        Arguments.of(new MyList(List.of(1, 2, 3)).contains(2), true),
        Arguments.of(new MyList(List.of(1, 2, 3)).contains(3), true),
        Arguments.of(new MyList(List.of(1, 2, 3)).contains(0), false));
  }

  @ParameterizedTest
  @MethodSource("argsForContains")
  void contain(boolean actual, boolean expected) {
    Assertions.assertEquals(actual, expected);
  }

  private static Stream<Arguments> argsForGet() {
    MyList actual = new MyList(List.of(1, 2, 3));
    return Stream.of(
        Arguments.of(actual.get(0), 1),
        Arguments.of(actual.get(1), 2),
        Arguments.of(actual.get(2), 3));
  }

  @ParameterizedTest
  @MethodSource("argsForGet")
  void get(Object actual, Object expected) {
    Assertions.assertEquals(actual, expected);
  }

  @Test
  @SuppressWarnings("ResultOfMethodCallIgnored")
  void getException() {
    boolean except = false;
    MyList list = new MyList(List.of(1, 2, 3));
    try {
      list.get(3);
    } catch (Exception e) {
      except = true;
    }
    Assertions.assertTrue(except);
  }

  private static Stream<Arguments> argsForAdd() {
    MyList actual = new MyList();
    return Stream.of(Arguments.of(actual.add(1), true), Arguments.of(actual.add(2), true));
  }

  @ParameterizedTest
  @MethodSource("argsForAdd")
  void add(boolean actual, boolean expected) {
    Assertions.assertEquals(actual, expected);
  }

  private static Stream<Arguments> argsForAddInPos() {
    MyList act1 = new MyList();
    MyList act2 = new MyList(List.of(1));
    MyList act3 = new MyList(List.of(1));
    MyList act4 = new MyList(List.of(1, 2));
    MyList act5 = new MyList(List.of(1, 2));
    MyList act6 = new MyList(List.of(1, 2));
    act1.add(0, 0);
    act2.add(1, 0);
    act3.add(1, 3);
    act4.add(2, 3);
    act5.add(0, 0);
    act6.add(1, 3);
    return Stream.of(
        Arguments.of(List.of(act1.get(0)), List.of(0)),
        Arguments.of(List.of(act2.get(0), act2.get(1)), List.of(1, 0)),
        Arguments.of(List.of(act3.get(0), act3.get(1)), List.of(1, 3)),
        Arguments.of(List.of(act4.get(0), act4.get(1), act4.get(2)), List.of(1, 2, 3)),
        Arguments.of(List.of(act5.get(0), act5.get(1), act5.get(2)), List.of(0, 1, 2)),
        Arguments.of(List.of(act6.get(0), act6.get(1), act6.get(2)), List.of(1, 3, 2)));
  }

  @ParameterizedTest
  @MethodSource("argsForAddInPos")
  void addInPos(List<Integer> act, List<Integer> exp) {
    Assertions.assertEquals(act, exp);
  }

  @Test
  void addException() {
    boolean except = false;
    MyList list = new MyList(List.of(1, 2, 3));
    try {
      list.add(5, 4);
    } catch (Exception e) {
      except = true;
    }
    Assertions.assertTrue(except);
  }

  private static Stream<Arguments> argsForRemove() {
    MyList actual = new MyList(List.of(1, 2, 3, 4));
    return Stream.of(
        Arguments.of(actual.remove(1), 2), // 1234 to 134
        Arguments.of(actual.remove(2), 4), // 134 to 13
        Arguments.of(actual.remove(0), 1) // 13 to 3
        );
  }

  @Test
  void removeException() {
    boolean except = false;
    MyList list = new MyList(List.of(1, 2, 3));
    try {
      list.remove(5);
    } catch (Exception e) {
      except = true;
    }
    Assertions.assertTrue(except);
  }

  @ParameterizedTest
  @MethodSource("argsForRemove")
  void removeMyList(Object act, Object exp) {
    Assertions.assertEquals(act, exp);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private static Stream<Arguments> unsupportedOperations() {
    MyList actual = new MyList();
    return Stream.of(
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::iterator)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::toArray)),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.toArray(new Object[0]))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.remove(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.containsAll(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.addAll(0, null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.removeAll(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.retainAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::clear)),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.set(0, null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.indexOf(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.lastIndexOf(null))),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::listIterator)),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.listIterator(0))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.subList(0, 0))));
  }

  @ParameterizedTest
  @MethodSource("unsupportedOperations")
  void unsupportedOperations(UnsupportedOperationException caught) {
    Assertions.assertTrue(caught.getMessage().contains("Данная операция не поддерживается"));
  }

  /** Тесты для queue */
  private static Stream<Arguments> argsForIsEmptyQueue() {
    MyQueue actual = new MyQueue();
    actual.add(1);
    return Stream.of(Arguments.of(new MyQueue(), true), Arguments.of(actual, false));
  }

  @ParameterizedTest
  @MethodSource("argsForIsEmptyQueue")
  void isEmptyQueue(MyQueue actual, boolean expected) {
    Assertions.assertEquals(actual.isEmpty(), expected);
  }

  private static Stream<Arguments> argsForAddQueue() {
    MyQueue actual = new MyQueue();
    return Stream.of(Arguments.of(actual.add(1), true), Arguments.of(actual.add(2), true));
  }

  @ParameterizedTest
  @MethodSource("argsForAddQueue")
  void addQueue(boolean actual, boolean expected) {
    Assertions.assertEquals(actual, expected);
  }

  private static Stream<Arguments> argsForPeekQueue() {
    MyQueue actual = new MyQueue();
    Object added = 1;
    actual.add(added);
    return Stream.of(Arguments.of(new MyQueue().peek(), null), Arguments.of(actual.peek(), added));
  }

  @ParameterizedTest
  @MethodSource("argsForPeekQueue")
  void peekQueue(Object actual, Object expected) {
    Assertions.assertEquals(actual, expected);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private static Stream<Arguments> unsupportedOperationsQueue() {
    MyQueue actual = new MyQueue();
    return Stream.of(
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::iterator)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::size)),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.contains(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::toArray)),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.toArray(new Object[0]))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.remove(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.containsAll(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.addAll(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.removeAll(null))),
        Arguments.of(
            Assertions.assertThrows(
                UnsupportedOperationException.class, () -> actual.retainAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::clear)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, () -> actual.offer(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::remove)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class, actual::poll)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::element)));
  }

  @ParameterizedTest
  @MethodSource("unsupportedOperationsQueue")
  void unsupportedOperationsMyQueue(UnsupportedOperationException caught) {
    Assertions.assertTrue(caught.getMessage().contains("Данная операция не поддерживается"));
  }
}
