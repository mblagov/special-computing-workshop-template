package ru.spbu.apcyb.svp.tasks;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * A custom implementation of the {@code Stack} class that uses a {@code Linkedlist} as its
 * underlying data structure.
 *
 * @param <T> the type of elements in this stack
 * @see Stack
 * @see Linkedlist
 */
public class StackCustom<T> extends Stack<T> {

  private Linkedlist<T> elementDatacust;

  public StackCustom() {
    this.elementDatacust = new Linkedlist<>();
  }

  @Override
  public T push(T item) {
    elementDatacust.add(item);
    return item;
  }

  @Override
  public synchronized T pop() {
    if (elementDatacust.isEmpty()) {
      throw new EmptyStackException();
    }
    return elementDatacust.remove(elementDatacust.size() - 1);
  }

  @Override
  public synchronized T peek() {
    if (elementDatacust.isEmpty()) {
      throw new EmptyStackException();
    }
    return elementDatacust.get(elementDatacust.size() - 1);
  }

  @Override
  public boolean empty() {
    return elementDatacust.isEmpty();
  }

  @Override
  public synchronized int size() {
    return elementDatacust.size();
  }

  @Override
  public synchronized boolean equals(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized int hashCode() {
    throw new UnsupportedOperationException();
  }
}
