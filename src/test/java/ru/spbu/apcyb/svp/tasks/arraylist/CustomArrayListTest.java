package ru.spbu.apcyb.svp.tasks.arraylist;

import java.util.ArrayList;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomArrayListTest {
  @Test
  void testEquals() {
    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{1, 2, 3});
    CustomArrayList<Integer> list3 = new CustomArrayList<>(new Integer[]{1, 2, 4});
    CustomArrayList<Integer> list4 = new CustomArrayList<>(new Integer[]{});
    CustomArrayList<Integer> list5 = new CustomArrayList<>();
    ArrayList<Integer> list6 = new ArrayList<>(
        IntStream.range(1, 4).boxed().toList());

    Assertions.assertEquals(list1, list2);
    Assertions.assertNotEquals(list1, list3);
    Assertions.assertNotEquals(list1, list4);
    Assertions.assertEquals(list4, list5);
    Assertions.assertNotEquals(list1, list6);

  }

  @Test
  void testAdd() {
    CustomArrayList<Integer> actual = new CustomArrayList<>(new Integer[]{});

    Integer[] expectedArray = new Integer[200];
    for (int i = 0; i < 200; i++) {
      expectedArray[i] = i;
      actual.add(i);
    }
    CustomArrayList<Integer> expected = new CustomArrayList<>(expectedArray);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testAddByIndex() {
    CustomArrayList<Integer> actual = new CustomArrayList<>(new Integer[]{1, 3, 4});
    actual.add(1, 2);
    CustomArrayList<Integer> expected = new CustomArrayList<>(new Integer[]{1, 2, 3, 4});

    Assertions.assertEquals(expected, actual);
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.add(8, 10));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.add(-1, 10));
  }

  @Test
  void testContains() {
    CustomArrayList<Double> testList = new CustomArrayList<>(new Double[]{1.0, -10.1, 13.0, 0.0, 0.0});

    Assertions.assertTrue(testList.contains(-10.1));
    Assertions.assertFalse(testList.contains(-10.0));
  }

  @Test
  void testRemoveByIndex() {
    CustomArrayList<Integer> expected = new CustomArrayList<>(new Integer[]{2, 3});
    CustomArrayList<Integer> actual = new CustomArrayList<>();
    actual.add(1);
    actual.add(2);
    actual.add(3);

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.remove(-1));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.remove(5));
    actual.remove(0);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testEmpty() {
    CustomArrayList<Double> notEmptyList = new CustomArrayList<>(new Double[]{3.0});
    CustomArrayList<Double> emptyList1 = new CustomArrayList<>(new Double[]{});
    CustomArrayList<Double> emptyList2 = new CustomArrayList<>();
    CustomArrayList<Double> emptyList3 = new CustomArrayList<>(new Double[]{1.0});
    emptyList3.remove(0);

    Assertions.assertTrue(emptyList1.isEmpty());
    Assertions.assertTrue(emptyList2.isEmpty());
    Assertions.assertTrue(emptyList3.isEmpty());
    Assertions.assertFalse(notEmptyList.isEmpty());

  }

  @Test
  void testGet() {
    CustomArrayList<Integer> testList = new CustomArrayList<>(new Integer[]{0, 1, 2, 3});

    for (int i = 0; i < 4; i++) {
      Assertions.assertEquals(i, testList.get(i));
    }
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(-1));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(5));

  }

  @Test
  void testSet() {
    CustomArrayList<Integer> actual = new CustomArrayList<>(new Integer[]{1, 2, 3, 4});
    CustomArrayList<Integer> expected = new CustomArrayList<>(new Integer[]{1, 2, -3, 4});
    Integer actualRemovedElem = actual.set(2, -3);
    int expectedRemovedElem = 3;

    Assertions.assertEquals(expectedRemovedElem, actualRemovedElem);
    Assertions.assertEquals(expected, actual);

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.set(-1, 2));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.set(4, 2));
  }

  @Test
  void testSize() {
    CustomArrayList<Integer> testList = new CustomArrayList<>();

    Assertions.assertEquals(0, testList.size());
    testList.add(1);
    testList.add(2);
    Assertions.assertEquals(2, testList.size());
    testList.remove(testList.size() - 1);
    Assertions.assertEquals(1, testList.size());
  }
}
//
//  @Test
//  void testAdd() {
//
//  }
//
//  @Test
//  void testAddIndex() {
//
//  }
//
//  @Test
//  void testRemoveIndex() {
//
//  }
//
//  @Test
//  void testClear() {
//
//  }
//
//  @Test
//  void testContains() {
//
//  }
//
//  @Test
//  void testIsEmpty() {
//    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
//    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{});
//    Assertions.assertFalse(list1.isEmpty());
//    Assertions.assertTrue(list2.isEmpty());
//  }
//
//  @Test
//  void testGet() {
//    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
//    int res = list1.get(2);
//    int ex = 3;
//    Assertions.assertEquals(ex, res);
//  }
//
//  @Test
//  void testEquals() {
//    CustomArrayList<Integer> list1 = new CustomArrayList<>(new Integer[]{1, 2, 3});
//    CustomArrayList<Integer> list2 = new CustomArrayList<>(new Integer[]{1, 2, 3});
//    CustomArrayList<Integer> list3 = new CustomArrayList<>(new Integer[]{1, 2, 4});
//    CustomArrayList<Integer> list4 = new CustomArrayList<>(new Integer[]{});
//    CustomArrayList<Integer> list5 = new CustomArrayList<>();
//    ArrayList<Integer> list6 = new ArrayList<>(
//        IntStream.range(1, 4).boxed().toList());
//
//    Assertions.assertEquals(list1, list2);
//    Assertions.assertNotEquals(list1, list3);
//    Assertions.assertNotEquals(list1, list4);
//    Assertions.assertEquals(list4, list5);
//    Assertions.assertNotEquals(list1, list6);
//  }

