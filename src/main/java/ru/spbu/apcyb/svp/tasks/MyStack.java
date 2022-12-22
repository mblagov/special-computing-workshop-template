package ru.spbu.apcyb.svp.tasks;


import java.io.Serializable;
import java.util.EmptyStackException;

/**
 * Задание 2.
 */
public class MyStack extends java.util.Stack<Object> implements Serializable{

    private final transient LinkedList stack;

    public MyStack() {
        stack = new LinkedList();
    }

    @Override
    public Object push(Object data) {
        stack.add(data);
        return data;
    }

    @Override
    public synchronized Object pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public synchronized Object peek() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    @Override
    public synchronized boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public synchronized int search(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized boolean equals(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized int hashCode() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}

