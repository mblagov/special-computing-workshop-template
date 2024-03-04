package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {
    private MyQueue<String> myQueue;

    @BeforeEach
    void setUp() {
        myQueue = new MyQueue<String>();
    }

    @Test
    void sizeIfEmpty() {
        assertEquals(0, myQueue.size());
    }

    @Test
    void sizeIfNotEmpty() {
        myQueue.add("element");
        assertEquals(1, myQueue.size());
    }

    @Test
    void isEmptyIfEmpty() {
        assertTrue(myQueue.isEmpty());
    }

    @Test
    void isEmptyIfNotEmpty() {
        myQueue.add("element");
        assertFalse(myQueue.isEmpty());
    }

    @Test
    void peekIfEmpty() {
        assertNull(myQueue.peek());
    }

    @Test
    void peekIfNotEmpty() {
        myQueue.add("elementOne");
        myQueue.add("elementTwo");
        assertEquals("elementOne", myQueue.peek());
    }

    @Test
    void add() {
        myQueue.add("element");
        assertEquals("element", myQueue.peek());
    }


    @Test
    void containsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myQueue.contains(" "));

    }

    @Test
    void


}