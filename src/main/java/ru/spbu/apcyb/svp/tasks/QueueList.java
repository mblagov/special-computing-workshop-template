package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;

/**
 * Реализация очереди.
 */

public class QueueList implements java.util.Queue<Object> {
  private final LinkedList queue;

  public QueueList() {
    queue = new LinkedList();
  }

  @Override
  public boolean add(Object data) { // Постановка в очередь
    queue.add(data);
    return true;
  }

  @Override
  public Object peek() { // Получение первого в очереди
    return queue.get(0);
  }

  @Override
  public boolean isEmpty() { // Проверка на пустоту
    return queue.isEmpty();
  }

  @Override
  public String toString() {
    return queue.toString();
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator iterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray(Object[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean offer(Object o) {
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
}
