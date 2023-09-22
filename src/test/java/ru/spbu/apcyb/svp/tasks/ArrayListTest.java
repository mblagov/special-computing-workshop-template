package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для линейного списка.
 */
class ArrayListTest {

    ArrayList list = new ArrayList();
    ArrayList list2 = new ArrayList();

    @Test
    void containsAll() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.containsAll(list2));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void addAll() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.addAll(list2));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void addAll2() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.addAll(0, list2));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testAddAll()throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.addAll(list2));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void removeAll() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.removeAll(list2));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void retainAll() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.retainAll(list2));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void clear() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.clear());
        assertEquals("", thrown.getMessage());
    }

    @Test
    void set() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.set(0, null));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void lastIndexOf() throws UnsupportedOperationException {
        ArrayList list = new ArrayList();
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf(null));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void listIterator() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, list::listIterator);
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testListIterator() throws UnsupportedOperationException {
        ArrayList list = new ArrayList();
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testSubList() throws UnsupportedOperationException {
        ArrayList list = new ArrayList();
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 1));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testToArray() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> {
                    Object[] a = new Object[0];
                    list.toArray(a);
                });
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testToArray2() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, list::toArray);
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testIterator() throws UnsupportedOperationException {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, list::iterator);
        assertEquals("", thrown.getMessage());
    }

    @Test
    void addIndexTest2() {
        IndexOutOfBoundsException thrown =
                assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 9));
        assertEquals("Неправильный индекс!", thrown.getMessage());
    }

    @Test
    void removeTest2() {
        IndexOutOfBoundsException thrown =
                assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertEquals("Неправильный индекс!", thrown.getMessage());
    }

    @Test
    void removeTest3() {
        UnsupportedOperationException thrown =
                assertThrows(UnsupportedOperationException.class, () -> list.remove(null));
        assertEquals("", thrown.getMessage());
    }

    @Test
    void getFirstTest() {
        ArrayList stack = new ArrayList();
        stack.addFirst(232);
        stack.add(1);
        stack.add(3);
        stack.add(4);
        assertEquals(232, stack.getFirst());
    }

    @Test
    void containsTest() {
        ArrayList stack = new ArrayList();
        stack.add(2);
        stack.add(2);
        stack.add(null);
        stack.add(3);
        assertTrue(stack.contains(3));
    }

    @Test
    void addIndexTest() {
        ArrayList stack = new ArrayList();
        stack.addFirst(0);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        stack.add(6);
        stack.add(4, 111);
        assertEquals(4, stack.indexOf(111));
    }

    @Test
    void isEmptyTest() {
        ArrayList stack = new ArrayList();
        stack.add(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void removeTest() {
        ArrayList stack = new ArrayList();
        stack.add(2);
        stack.add(1);
        stack.add(null);
        stack.add(2);
        stack.add(3);
        stack.remove(0);
        assertEquals(1, stack.getFirst());
    }

    @Test
    void removeFirstTest() {
        ArrayList stack = new ArrayList();
        stack.add(3);
        stack.add(2);
        stack.add(null);
        stack.add(5);
        stack.add(1);
        stack.removeFirst();
        assertEquals(2, stack.getFirst());
    }

    @Test
    void sizeTest() {
        ArrayList stack = new ArrayList();
        stack.add(2);
        stack.add(1);
        stack.add(null);
        stack.add(2);
        stack.add(3);
        stack.add(2);
        stack.add(1);
        stack.remove(1);
        assertEquals(6, stack.size());
    }

    @Test
    void indexOfTest1() {
        ArrayList stack = new ArrayList();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(1);
        stack.add(0);
        assertEquals(-1, stack.indexOf(100));
    }

    @Test
    void indexOfTest2() {
        ArrayList stack = new ArrayList();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(1);
        stack.add(0);
        assertEquals(5, stack.indexOf(0));
    }

}