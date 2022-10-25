package ru.spbu.apcyb.svp.tasks;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Тесты для задания 2.
 */
class Task2Test {

  private static Stream<Arguments> provideArgsForIsEmptyMyList() {
    return Stream.of(
        Arguments.of(new MyList(), true),
        Arguments.of(new MyList(List.of(1)), false)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForIsEmptyMyList")
  void isEmptyMyList(MyList act, boolean exp) {
    Assertions.assertEquals(act.isEmpty(), exp);
  }

  private static Stream<Arguments> provideArgsForContainsMyList() {
    return Stream.of(
        Arguments.of(new MyList().contains(1), false),
        Arguments.of(new MyList(List.of(1, 2)).contains(1), true),
        Arguments.of(new MyList(List.of(1, 2)).contains(2), true),
        Arguments.of(new MyList(List.of(1, 2)).contains(0), false)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForContainsMyList")
  void containsMyList(boolean act, boolean exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideArgsForSizeMyList() {
    return Stream.of(
        Arguments.of(new MyList().size(), 0),
        Arguments.of(new MyList(List.of()).size(), 0),
        Arguments.of(new MyList(List.of(1)).size(), 1),
        Arguments.of(new MyList(List.of(1, 2)).size(), 2)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForSizeMyList")
  void sizeMyList(int act, int exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideArgsForAddMyList() {
    MyList actual = new MyList();
    return Stream.of(
        Arguments.of(actual.add(1), true),
        Arguments.of(actual.add(2), true)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForAddMyList")
  void addMyList(boolean act, boolean exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideArgsForAddByIndexMyList() {
    MyList act1 = new MyList();
    MyList act2 = new MyList(List.of(1));
    MyList act3 = new MyList(List.of(0));
    act1.add(0, 0);
    act2.add(0, 0);
    act3.add(1, 1);
    return Stream.of(
        Arguments.of(List.of(act1.get(0)), List.of(0)),
        Arguments.of(List.of(act2.get(0), act2.get(1)), List.of(0, 1)),
        Arguments.of(List.of(act3.get(0), act3.get(1)), List.of(0, 1))
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForAddByIndexMyList")
  void addByIndexMyList(List<Integer> act, List<Integer> exp) {
    Assertions.assertEquals(act, exp);
  }


  private static Stream<Arguments> provideArgsForGetMyList() {
    MyList actual = new MyList(List.of(0, 1, 2));
    return Stream.of(
        Arguments.of(actual.get(0), 0),
        Arguments.of(actual.get(1), 1),
        Arguments.of(actual.get(2), 2)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForGetMyList")
  void getMyList(Object act, Object exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideArgsForRemoveMyList() {
    MyList actual = new MyList(List.of(0, 1, 2, 3));
    return Stream.of(
        Arguments.of(actual.remove(1), 1), //(0123)->(023)
        Arguments.of(actual.remove(2), 3), //(023)->(02)
        Arguments.of(actual.remove(0), 0) //(02)->(2)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForRemoveMyList")
  void removeMyList(Object act, Object exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideUnsupportedOperationsMyList() {
    MyList actual = new MyList();
    return Stream.of(
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::iterator)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::toArray)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.toArray(new Object[0]))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.remove(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.containsAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.addAll(0, null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.removeAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.retainAll(null))),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::clear)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class,
                () -> actual.set(0, null))),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class,
                () -> actual.indexOf(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.lastIndexOf(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            actual::listIterator)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.listIterator(0))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.subList(0, 0)))
    );
  }

  @ParameterizedTest
  @MethodSource("provideUnsupportedOperationsMyList")
  void unsupportedOperationsMyList(UnsupportedOperationException caught) {
    Assertions.assertTrue(caught.getMessage().contains("UnsupportedOperationException"));
  }

  private static Stream<Arguments> provideOutOfBoundsMyList() {
    MyList act = new MyList(List.of(0, 1));
    return Stream.of(
        Arguments.of(
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> act.add(Integer.MAX_VALUE, null))),
        Arguments.of(
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> act.remove(Integer.MAX_VALUE))),
        Arguments.of(
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> act.get(Integer.MAX_VALUE)))
    );
  }

  @ParameterizedTest
  @MethodSource("provideOutOfBoundsMyList")
  void outOfBoundsOperationsMyList(IndexOutOfBoundsException caught) {
    Assertions.assertTrue(caught.getMessage().contains("IndexOutOfBoundsException"));
  }

  private static Stream<Arguments> provideArgsForIsEmptyMyQueue() {
    MyQueue act = new MyQueue();
    act.add(1);
    return Stream.of(
        Arguments.of(new MyQueue(), true),
        Arguments.of(act, false)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForIsEmptyMyQueue")
  void isEmptyMyQueue(MyQueue act, boolean exp) {
    Assertions.assertEquals(act.isEmpty(), exp);
  }

  private static Stream<Arguments> provideArgsForAddMyQueue() {
    MyQueue actual = new MyQueue();
    return Stream.of(
        Arguments.of(actual.add(1), true),
        Arguments.of(actual.add(2), true)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForAddMyQueue")
  void addMyQueue(boolean act, boolean exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideArgsForPeekMyQueue() {
    MyQueue act = new MyQueue();
    Object added = 1;
    act.add(added);
    return Stream.of(
        Arguments.of(new MyQueue().peek(), null),
        Arguments.of(act.peek(), added)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArgsForPeekMyQueue")
  void peekMyQueue(Object act, Object exp) {
    Assertions.assertEquals(act, exp);
  }

  private static Stream<Arguments> provideUnsupportedOperationsMyQueue() {
    MyQueue actual = new MyQueue();
    return Stream.of(
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::iterator)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::size)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.contains(null))),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::toArray)),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.toArray(new Object[0]))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.remove(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.containsAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.addAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.removeAll(null))),
        Arguments.of(Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.retainAll(null))),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::clear)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, () -> actual.offer(null))),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::remove)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::poll)),
        Arguments.of(
            Assertions.assertThrows(UnsupportedOperationException.class, actual::element))
    );
  }

  @ParameterizedTest
  @MethodSource("provideUnsupportedOperationsMyQueue")
  void unsupportedOperationsMyQueue(UnsupportedOperationException caught) {
    Assertions.assertTrue(caught.getMessage().contains("UnsupportedOperationException"));
  }
}
