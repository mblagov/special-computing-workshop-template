package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import javax.annotation.Nonnull;

/**
 * Queue
 */
public class Queue2 implements Queue<Object> {
  private final DoubleLinkedList dll;

  public Queue2() {
    this.dll = new DoubleLinkedList();
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEmpty() {
    return this.dll.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<Object> iterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    return this.dll.toArray();
  }

  @Override
  public <T> T[] toArray(@Nonnull T[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean add(Object o) {
    return this.dll.add(o);
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(@Nonnull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(@Nonnull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(@Nonnull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(@Nonnull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean offer(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object poll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object element() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object peek() {
    Object result = null;
    if (!this.isEmpty()) {
      result = this.dll.get(0);
    }
    return result;
  }
}