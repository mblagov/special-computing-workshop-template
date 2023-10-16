package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack extends Stack {
    private final int DEFAULT_CAPACITY = 2;
    public MyStack() {
        this.elementCount = 0;
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyStack(int capacity) {
        if (capacity > 0) {
            this.elementCount = 0;
            this.elementData = new Object[capacity];
            return;
        }
        throw new ExceptionInInitializerError("Capacity must be positive integer, provided: " + capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MyStack) {
            if (this.elementCount == ((MyStack) o).elementCount) {
                for (int i = 0; i < this.elementCount; i++) {
                    if (!this.get(i).equals(((MyStack) o).get(i))) return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Capacity: ").append(this.elementData.length).append("\n");
        res.append("Size: ").append(this.elementCount).append("\n");
        for (int i = 0; i < this.elementCount; i++) {
            res.append(this.elementData[i].toString()).append(" ");
        }
        res.append("\n");
        return res.toString();
    }

    public Object push(Object o) {
        if (this.elementCount == this.elementData.length) {
            this.elementData = Arrays.copyOf(this.elementData, this.elementData.length * 2);
        }
        this.elementData[elementCount++] = o;
        return o;
    }

    public Object pop() {
        Object o = this.peek();
        this.elementData = Arrays.copyOf(this.elementData, --elementCount);
        return o;
    }

    public Object peek() {
        if (elementCount > 0) return this.elementData[elementCount-1];
        throw new EmptyStackException();
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }

}
