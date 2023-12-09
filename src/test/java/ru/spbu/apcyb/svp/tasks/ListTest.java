package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ListTest {

    @Test
    public void listSizeTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        assertEquals(1, newList.size());
    }

    @Test
    public void isEmptyTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        assertTrue(newList.isEmpty());

        newList.add(5);
        assertFalse(newList.isEmpty());
    }

    @Test
    public void containsTrueTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(1);
        newList.add("a");
        assertTrue(newList.contains(1));
    }

    @Test
    public void containsFalseTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(1);
        newList.add("a");
        assertFalse(newList.contains(10));
    }

    @Test
    public void indexOfTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(1);
        newList.add("a");
        newList.add(55);
        assertEquals(2,newList.indexOf("a"));
    }

    @Test
    public void indexOfNoElementTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(1);
        newList.add("a");
        newList.add(55);
        assertEquals(-1,newList.indexOf(555));
    }

    @Test
    public void addObjectTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        assertTrue(newList.add(7));
        assertEquals(1,newList.size());
        assertEquals(7, newList.get(0));
    }

    @Test
    public void addObjectSeveralTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        assertTrue(newList.add(7));
        assertTrue(newList.add(5));
        assertTrue(newList.add(3));
        assertEquals(3,newList.size());
        assertEquals(7, newList.get(0));
        assertEquals(5, newList.get(1));
        assertEquals(3, newList.get(2));
    }

    @Test
    public void addByIndexTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(0,7);
        assertEquals(1,newList.size());
        assertEquals(0, newList.indexOf(7));
    }


    @Test
    public void addByIndexBeginTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(10);
        newList.add(5);
        newList.add(0, 8);
        assertEquals(4,newList.size());
        assertEquals(0, newList.indexOf(8));
    }

    @Test
    public void addByIndexMiddleTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(10);
        newList.add(5);
        newList.add(1, 8);
        assertEquals(4,newList.size());
        assertEquals(1, newList.indexOf(8));
    }

    @Test
    public void addByIndexEndTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(10);
        newList.add(5);
        newList.add(3, 8);
        assertEquals(4,newList.size());
        assertEquals(3, newList.indexOf(8));
    }

    @Test
    public void addByIndexEmptyTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(0, 8);
        assertEquals(1,newList.size());
        assertEquals(0, newList.indexOf(8));
    }

    @Test
    public void addByIndexOutOfBoundsTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.add(10);
        newList.add(5);
        assertEquals(3, newList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> newList.add(4, 8));
        assertThrows(IndexOutOfBoundsException.class, () -> newList.add(-1, 8));
    }

    @Test
    public void removeByIndexTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(7);
        newList.remove(0);
        assertEquals(0,newList.size());
    }

    @Test
    public void removeByIndexBeginTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(10);
        newList.add(5);
        assertEquals(3,newList.remove(0));
        assertEquals(3,newList.size());
    }

    @Test
    public void removeByIndexMiddleTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(10);
        newList.add(5);
        assertEquals(10,newList.remove(2));
        assertEquals(3,newList.size());
    }

    @Test
    public void removeByIndexEndTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(10);
        newList.add(5);
        assertEquals(5,newList.remove(3));
        assertEquals(3,newList.size());
    }

    @Test
    public void removeByIndexEmptyTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        assertTrue(newList.isEmpty());
        assertThrows(NullPointerException.class, () -> newList.remove(2));
    }

    @Test
    public void removeByIndexOutOfBoundsTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(10);
        newList.add(5);
        assertEquals(4, newList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> newList.remove(5));
        assertThrows(IndexOutOfBoundsException.class, () -> newList.remove(-1));
    }

    @Test
    public void removeObjectBeginTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add("a");
        newList.add(7);
        newList.add(10);
        newList.add(5);
        assertTrue(newList.remove("a"));
        assertEquals(3,newList.size());
    }

    @Test
    public void removeObjectMiddleTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add("a");
        newList.add(5);
        assertTrue(newList.remove("a"));
        assertEquals(3,newList.size());
    }

    @Test
    public void removeObjectEndTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(9);
        newList.add("a");
        assertTrue(newList.remove("a"));
        assertEquals(3,newList.size());
    }

    @Test
    public void removeObjectEmptyTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        assertTrue(newList.isEmpty());
        assertThrows(NullPointerException.class, () -> newList.remove("A"));
    }

    @Test
    public void removeObjectNoElementTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        assertEquals(2, newList.size());
        assertThrows(NoSuchElementException.class, () -> newList.remove("a"));
    }


    @Test
    public void getBeginTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(9);
        newList.add("a");
        assertEquals(3, newList.get(0));
    }

    @Test
    public void getMiddleTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(9);
        newList.add("a");
        assertEquals(7, newList.get(1));
    }

    @Test
    public void getEndTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        newList.add(7);
        newList.add(9);
        newList.add("a");
        assertEquals("a", newList.get(3));
    }

    @Test
    public void getEmptyTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        assertThrows(NullPointerException.class, () -> newList.get(2));
    }

    @Test
    public void getIndexOutOfBoundsTest() {
        DoublyLinkedLinearList newList = new DoublyLinkedLinearList();
        newList.add(3);
        assertEquals(1,newList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> newList.get(5));
    }
















}
