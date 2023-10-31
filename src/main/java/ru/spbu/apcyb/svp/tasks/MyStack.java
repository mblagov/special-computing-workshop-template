package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack extends Stack {
    private MyArrayList myList;
    public MyStack() {
        this.myList = new MyArrayList();
    }

    public MyStack(int capacity) {
        this.myList = new MyArrayList(capacity);
    }

    public MyArrayList getData() {
        return myList;
    }

    @Override
    public boolean equals(Object o) {
        return myList.equals(((MyStack) o).getData());
    }

    @Override
    public String toString() {
        return myList.toString();
    }

    public Object push(Object o) {
        myList.add(o);
        return o;
    }

    public Object pop() {
        return myList.remove(myList.size() - 1);
    }

    public Object peek() {
        return myList.get(myList.size() - 1);
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }
}
