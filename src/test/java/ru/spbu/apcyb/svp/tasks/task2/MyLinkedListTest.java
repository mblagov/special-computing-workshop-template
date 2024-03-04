package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<String> linkedList;

    @BeforeEach
    void setUp(){
        linkedList = new MyLinkedList<String>();
    }

    @Test
    void isEmptyIfEmpty() {
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void isEmptyIfNotEmpty() {
        linkedList.add("element");
        assertFalse(linkedList.isEmpty());
    }

    @Test
    void getIfIndexIsNegative() {
        linkedList.add("element");
        assertNull(linkedList.get(-1));
    }

    @Test
    void getIfIndexIsBiggerThanTailIndex() {
        linkedList.add("element");
        assertNull(linkedList.get(1));
    }

    @Test
    void getIfIndexIsCorrect() {
        linkedList.add("element");
        assertEquals("element", linkedList.get(0));
    }

    @Test
    void containsIfFalse() {
        linkedList.add("1");
        assertFalse(linkedList.contains("2"));
    }

    @Test
    void containsIfTrue() {
        linkedList.add("1");
        assertTrue(linkedList.contains("1"));
    }

    @Test
    void addInTheEnd() {
        linkedList.add("1");
        assertEquals("1", linkedList.get(0));

    }

    @Test
    void addByTheIndex() {
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("3");
        linkedList.add(2, "2");
        assertEquals("2", linkedList.get(2));
    }

    @Test
    void removeIfListIsNotEmpty() {
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.remove(1);
        assertEquals("2", linkedList.get(1));
    }

    @Test
    void removeIfListIsEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(2));
    }

    @Test
    void removeIfIndexOfElementIsBiggerThanListSize() {
        linkedList.add("0");
        linkedList.add("1");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(2));
    }

    @Test
    void sizeIfListIsEmpty() {
        assertEquals(0, linkedList.size());
    }

    @Test
    void sizeIfListIsNotEmpty() {
        linkedList.add("element");
        assertEquals(1, linkedList.size());

    }
}
