package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayListTests {
    MyArrayList list = new MyArrayList();

    @Test
    public void emptyList() {
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void emptyListsEquality() {
        MyArrayList list1 = new MyArrayList(10);
        Assertions.assertEquals(list, list1);
    }

    @Test
    public void zeroSizeTest() {
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void notZeroSizeTest() {
        for (int i = 0; i < 3; i++) {
            list.add(i + "fa");
        }
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void addRemoveEnd() {
        MyArrayList list1 = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list.add(i);
        }
        list.add(new MyArrayList());
        list.remove(list.size() - 1);
        Assertions.assertEquals(list, list1);
    }

    @Test
    public void notEqualsTest() {
        MyArrayList list1 = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list.add(i);
        }
        list.remove(5);
        Assertions.assertNotEquals(list, list1);
    }

    @Test
    public void containsTestT() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        Assertions.assertTrue(list.contains(6 + "fa"));
    }

    @Test
    public void containsTestF() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        Assertions.assertFalse(list.contains(10 + "fa"));
    }

    @Test
    public void removeCheck() {
        MyArrayList list1 = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            if (i != 6) {
                StringBuilder s = new StringBuilder();
                s.append("a".repeat(i));
                list1.add(s.toString());
            }
            StringBuilder s = new StringBuilder();
            s.append("a".repeat(i));
            list.add(s.toString());
        }
        list.remove(6);
        Assertions.assertEquals(list, list1);
    }

    @Test
    public void getTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        Assertions.assertEquals("5fa", list.get(5));
    }

    @Test
    public void getNegativeIndexTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(-3),
                "Negative index exception must be thrown"
        );
        Assertions.assertEquals("Element index must be equals to or more than 0 " +
                "and less than size, current size: " + list.size() + ", index provided: " + -3, e.getMessage());
    }

    @Test
    public void getBigIndexTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(10),
                "Big index exception must be thrown"
        );
        Assertions.assertEquals("Element index must be equals to or more than 0 " +
                "and less than size, current size: " + list.size() + ", index provided: " + 10, e.getMessage());
    }

    @Test
    public void removeBigIndexTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(10),
                "Big index exception must be thrown"
        );
        Assertions.assertEquals("Element index must be equals to or more than 0 " +
                "and less than size, current size: " + list.size() + ", index provided: " + 10, e.getMessage());
    }

    @Test
    public void capacityAffect() {
        MyArrayList list1 = new MyArrayList();
        for (int i = 0; i < 2; i++) {
            list1.add(i + "fa");
            list.add(i + "fa");
        }
        list.add(5);
        list.remove(2);
        Assertions.assertEquals(list, list1);
    }

    @Test
    public void unsupportedOperationTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "fa");
        }
        UnsupportedOperationException e = Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> list.clear(),
                "Unsupported operation"
        );
    }
}
