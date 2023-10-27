package ru.spbu.apcyb.svp.tasks.stack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * Stack's own implementation.
 *
 * @param <T> type of stored data
 */
public class MyStack<T> extends Stack<T> {

  private static final int INITIAL_SIZE = 10;
  transient Object[] data;
  private int currentMaxSize;
  private int size;

  /**
   * Creating an empty stack.
   */
  public MyStack() {
    data = new Object[INITIAL_SIZE];
    currentMaxSize = data.length;
    size = 0;
  }

  /**
   * Creating a stack based on the src array.
   *
   * @param src array on the basis of which the stack will be created
   */
  public MyStack(T[] src) {
    data = Arrays.copyOf(src, src.length);
    currentMaxSize = data.length;
    size = src.length;
  }

  /**
   * Doubles the data array (or makes its size equal to 10 if its size is 0).
   */
  private void resize() {
    Object[] newData;
    if (data.length == 0) {
      newData = new Object[10];
    } else {
      newData = new Object[data.length * 2];
    }

    System.arraycopy(data, 0, newData, 0, data.length);
    data = newData;
    currentMaxSize = data.length;
  }

  @Override
  public T push(T item) {
    if (size == currentMaxSize) {
      resize();
    }
    data[size] = item;
    size++;
    return item;
  }

  @Override
  @SuppressWarnings("unchecked")
  public synchronized T pop() {
    if (isEmpty()) {
      throw new IndexOutOfBoundsException();
    }
    T lastElem = (T) data[size - 1];
    size--;
    return lastElem;
  }

  @Override
  @SuppressWarnings("unchecked")
  public synchronized T peek() {
    if (isEmpty()) {
      return null;
    }
    return (T) data[size - 1];
  }

  @Override
  public synchronized boolean isEmpty() {
    return size == 0;
  }

  @Override
  public synchronized boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    if (size != ((MyStack<?>) obj).size) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (!Objects.equals(data[i], ((MyStack<?>) obj).data[i])) {
        return false;
      }
    }
    return true;
  }

  @Override
  public synchronized int hashCode() {
    return size;
  }
}
