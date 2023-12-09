package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueTest {

    @Test
    public void queueSizeTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(7);
        assertEquals(1, newQueue.size());
    }

    @Test
    public void isEmptyTrueTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertTrue(newQueue.isEmpty());
        newQueue.add(1);
        newQueue.add(2);
        newQueue.remove(1);
        newQueue.remove(2);
        assertTrue(newQueue.isEmpty());
    }

    @Test
    public void isEmptyFalseTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(5);
        assertFalse(newQueue.isEmpty());
    }

    @Test
    public void containsTrueTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(7);
        newQueue.add(1);
        newQueue.add("a");
        assertTrue(newQueue.contains(1));
    }

    @Test
    public void containsFalseTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(7);
        newQueue.add(1);
        newQueue.add("a");
        assertFalse(newQueue.contains(10));
    }

    @Test
    public void addTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertTrue(newQueue.add(7));
        assertEquals(1,newQueue.size());
        assertEquals(7, newQueue.peek());
    }

    @Test
    public void addObjectSeveralTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertTrue(newQueue.add(7));
        assertTrue(newQueue.add(5));
        assertTrue(newQueue.add(3));
        assertEquals(3,newQueue.size());
        assertTrue(newQueue.contains(7));
        assertTrue(newQueue.contains(5));
        assertTrue(newQueue.contains(3));
    }

    @Test
    public void removeTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(7);
        assertEquals(7,newQueue.remove());
        assertEquals(0,newQueue.size());
    }

    @Test
    public void removeSeveralTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add(10);
        newQueue.add(5);
        assertEquals(3,newQueue.remove());
        assertEquals(3,newQueue.size());
    }

    @Test
    public void removeEmptyTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertTrue(newQueue.isEmpty());
        assertThrows(NullPointerException.class, newQueue::remove);
    }

    @Test
    public void removeObjectBeginTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add("a");
        newQueue.add(7);
        newQueue.add(10);
        newQueue.add(5);
        assertTrue(newQueue.remove("a"));
        assertEquals(3,newQueue.size());
    }

    @Test
    public void removeObjectMiddleTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add("a");
        newQueue.add(5);
        assertTrue(newQueue.remove("a"));
        assertEquals(3,newQueue.size());
    }

    @Test
    public void removeObjectEndTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add(9);
        newQueue.add("a");
        assertTrue(newQueue.remove("a"));
        assertEquals(3,newQueue.size());
    }

    @Test
    public void removeObjectEmptyTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertTrue(newQueue.isEmpty());
        assertThrows(NullPointerException.class, () -> newQueue.remove("A"));
    }

    @Test
    public void removeObjectNoElementTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add(10);
        newQueue.add(5);
        assertFalse(newQueue.contains("a"));
        assertThrows(NoSuchElementException.class, () -> newQueue.remove("a"));
    }

    @Test
    public void pollTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(7);
        assertEquals(7,newQueue.poll());
        assertEquals(0,newQueue.size());
    }

    @Test
    public void pollSeveralTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add(10);
        newQueue.add(5);
        assertEquals(3,newQueue.poll());
        assertEquals(3,newQueue.size());
    }

    @Test
    public void pollEmptyTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertNull(newQueue.poll());
    }


    @Test
    public void elementTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add(9);
        newQueue.add("a");
        assertEquals(3, newQueue.element());
    }

    @Test
    public void elementEmptyTest() {
        QueueRealisation newQueue = new QueueRealisation();
        assertThrows(NullPointerException.class, newQueue::element);
    }

    @Test
    public void peekTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(3);
        newQueue.add(7);
        newQueue.add(9);
        newQueue.add("a");
        assertEquals(3, newQueue.peek());
    }

    @Test
    public void peekEmptyTest() {
        QueueRealisation newQueue = new QueueRealisation();
        newQueue.add(null);
        assertNull(newQueue.peek());
    }

 }
