package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import javax.annotation.Nullable;

public class MyQueue implements Queue<Object> {

  private static final String EXCEPT = "Данная операция не поддерживается";

  private final MyList head;

  public MyQueue() {
    this.head = new MyList();
  }

  @Override
  public boolean add(Object o) {
    this.head.add(o);
    return true;
  }

  @Override
  public Object peek() {
    Object result = null;
    if (!this.isEmpty()) {
      result = this.head.get(0);
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    return this.head.isEmpty();
  }

  @Override
  public boolean addAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public boolean contains(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public boolean containsAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public Object element() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public boolean offer(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public Object poll() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public Object remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public boolean removeAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public boolean retainAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public int size() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }

  @Override
  public <T> T[] toArray(@Nullable T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT);
  }
}
