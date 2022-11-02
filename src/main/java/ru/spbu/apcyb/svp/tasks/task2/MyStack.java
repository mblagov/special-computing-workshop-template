package ru.spbu.apcyb.svp.tasks.task2;

import java.util.EmptyStackException;

public class MyStack extends java.util.Stack<Object> {

  private final LinearList data;

  public MyStack() {
    data = new LinearList();
  }

  @Override
  public Object push(Object item) {
    data.add(item);
    return item;
  }

  @Override
  public synchronized Object pop() throws EmptyStackException {
    if (data.isEmpty()) {
      throw new EmptyStackException();
    }
    return data.remove(data.getSize() - 1);
  }

  @Override
  public synchronized Object peek() throws EmptyStackException {
    if (data.isEmpty()) {
      throw new EmptyStackException();
    }
    return data.get(data.getSize() - 1);
  }

  @Override
  public boolean empty() {
    return data.isEmpty();
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