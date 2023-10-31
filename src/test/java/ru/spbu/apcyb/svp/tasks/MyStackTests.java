package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyStackTests {
    MyStack stack = new MyStack();

    @Test
    public void emptyList() {
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    public void emptyListsEquality() {
        MyStack list1 = new MyStack(10);
        Assertions.assertEquals(stack, list1);
    }

    @Test
    public void addRemoveEnd() {
        MyStack stack1 = new MyStack();
        for (int i = 0; i < 10; i++) {
            stack1.push(i);
            stack.push(i);
        }
        stack.push(new MyArrayList());
        stack.pop();
        Assertions.assertEquals(stack, stack1);
    }

    @Test
    public void notEqualsTest() {
        MyStack stack1 = new MyStack();
        for (int i = 0; i < 10; i++) {
            stack1.push(i);
            stack.push(i);
        }
        stack.pop();
        Assertions.assertNotEquals(stack, stack1);
    }

    @Test
    public void peekTestT() {
        for (int i = 0; i < 10; i++) {
            stack.push(i + "fa");
        }
        Assertions.assertEquals(9 + "fa", stack.peek());
    }

    @Test
    public void peekTestF() {
        for (int i = 0; i < 10; i++) {
            stack.push(i + "fa");
        }
        Assertions.assertNotEquals(6 + "fa", stack.peek());
    }

    @Test
    public void removeCheck() {
        MyStack stack1 = new MyStack(20);
        for (int i = 0; i < 10; i++) {
            if (i != 9) {
                stack1.push("a".repeat(i));
            }
            stack.push("a".repeat(i));
        }
        stack.pop();
        Assertions.assertEquals(stack, stack1);
    }
}
