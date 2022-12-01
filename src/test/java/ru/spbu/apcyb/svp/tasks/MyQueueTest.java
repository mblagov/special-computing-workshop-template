package ru.spbu.apcyb.svp.tasks;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyQueueTest {


    @Test
    @DisplayName("addTest")
    void addTest() {
        MyQueue myQueue = new MyQueue();
        myQueue.add("str");
        Assertions.assertEquals("str", myQueue.peek());
    }

    @Test
    @DisplayName("sizeTest")
    void sizeTest() {
        MyQueue myQueue = new MyQueue();
        Assertions.assertEquals(0, myQueue.size());
        myQueue.add(5);
        Assertions.assertEquals(1, myQueue.size());
    }

    @Test
    @DisplayName("sizeTest2")
    void sizeTest2() {
        MyQueue myQueue = new MyQueue();
        myQueue.add(5);
        Assertions.assertEquals(1, myQueue.size());
    }

    @Test
    @DisplayName("pollTest")
    void pollTest() {
        MyQueue myQueue = new MyQueue();
        myQueue.add("str");
        myQueue.add(5);
        Object element = myQueue.poll();
        assertAll(
                () -> Assertions.assertEquals("str", element),
                () -> Assertions.assertArrayEquals(new Object[]{5}, myQueue.toArray())
        );
    }

    @Test
    @DisplayName("pollTest2")
    void pollTest2() {
        MyQueue myQueue = new MyQueue();
        Assertions.assertNull(myQueue.poll());
    }

    @Test
    @DisplayName("elementTest")
    void elementTest() {

        MyQueue myQueue = new MyQueue();
        myQueue.add("str");
        myQueue.add(5);
        Object element = myQueue.element();
        assertAll(
                () -> Assertions.assertEquals("str", element),
                () -> Assertions.assertArrayEquals(new Object[]{"str", 5}, myQueue.toArray())
        );
    }

    @Test
    @DisplayName("elementExceptionTest")
    void elementExceptionTest() {
        MyQueue myQueue = new MyQueue();
        Assertions.assertThrows(NoSuchElementException.class, () -> myQueue.element());
    }

    @Test
    @DisplayName("removeTest")
    void removeTest() {

        MyQueue myQueue = new MyQueue();
        myQueue.add("str");
        myQueue.add(5);
        Object element = myQueue.remove();
        assertAll(
                () -> Assertions.assertEquals("str", element),
                () -> Assertions.assertArrayEquals(new Object[]{5}, myQueue.toArray())
        );
    }

    @Test
    @DisplayName("removeExceptionTest")
    void removeExceptionTest() {
        MyQueue myQueue = new MyQueue();
        Assertions.assertThrows(NoSuchElementException.class, () -> myQueue.remove());
    }

    @Test
    @DisplayName("removeTest2")
    void removeTest2() {
        MyQueue myQueue = new MyQueue();
        myQueue.add("a");
        myQueue.add("g");
        myQueue.remove("g");
        assertArrayEquals(new Object[]{"a"}, myQueue.toArray());
    }

    @Test
    @DisplayName("peekTest")
    void peekTest() {
        MyQueue myQueueJr = new MyQueue();
        myQueueJr.add("str");
        myQueueJr.add(5);
        Object element = myQueueJr.peek();
        assertAll(
                () -> Assertions.assertEquals("str", element),
                () -> Assertions.assertArrayEquals(new Object[]{"str", 5}, myQueueJr.toArray())
        );
    }

    @Test
    @DisplayName("peekTest2")
    void peekTest2() {
        MyQueue myQueueJr = new MyQueue();
        Assertions.assertNull(myQueueJr.peek());
    }

    @Test
    @DisplayName("isEmptyTest")
    void isEmptyTest() {
        MyQueue blankMyQueue = new MyQueue();

        assertTrue(blankMyQueue.isEmpty());

    }

    @Test
    @DisplayName("isEmptyTest2")
    void isEmptyTest2() {
        MyQueue myQueue = new MyQueue();
        myQueue.add(1);
        assertFalse(myQueue.isEmpty());
    }

    @Test
    @DisplayName("clearTest")
    void clearTest() {
        MyQueue myQueue = new MyQueue();
        myQueue.add(new Integer[]{3, 6, 5});
        myQueue.clear();
        assertTrue(myQueue.isEmpty());
    }

}