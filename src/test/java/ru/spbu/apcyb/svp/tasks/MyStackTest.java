package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для стека.
 */
class MyStackTest {
    MyStack stack = new MyStack();

    @Test
    void testPush() {
        MyStack stack = new MyStack();
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.peek());
    }

    @Test
    void testPop() {
        MyStack stack = new MyStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        assertEquals(6, stack.pop());
    }

    @Test
    void testPop2() {
        boolean thrown = true;
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        try {
            stack.pop();
        } catch (RuntimeException e) {
            thrown = false;
        }
        assertFalse(thrown);
    }

    @Test
    void testPeek() {
        MyStack stack = new MyStack();
        stack.push(11);
        stack.push(12);
        stack.push(13);
        assertEquals(13, stack.peek());
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }
}
