package ru.spbu.apcyb.svp.tasks.second;

import java.util.AbstractSequentialList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * My double-linked list.
 *
 * @apiNote Java's {@link java.util.LinkedList} is a double-linked
 * @implNote Was used {@link AbstractSequentialList} abstract class
 *           that implements some methods by using {@link Iterator}.
 */
public class DoubleLinkedList<T> extends AbstractSequentialList<T> implements List<T>, Deque<T> {
  private int size = 0;

  Node<T> head = null;
  Node<T> tail = null;

  /**
   * Unlinks non-null node.
   */
  private T unlink(Node<T> cur) {
    assert cur != null;

    final Node<T> next = cur.next;
    final Node<T> prev = cur.prev;

    if (prev == null) {
      head = next;
    } else {
      prev.next = next;
      cur.prev = null;
    }

    if (next == null) {
      tail = prev;
    } else {
      next.prev = prev;
      cur.next = null;
    }

    size--;
    return cur.destruct();
  }

  // Add operations

  @Override
  public boolean add(T el) {
    addLast(el);
    return true;
  }

  @Override
  public void addFirst(T el) {
    if (head == null) {
      head = new Node<>(el);
      tail = head;
    } else {
      head = new Node<>(null, el, head);
      head.next.prev = head;
    }
    size++;
  }

  @Override
  public void addLast(T el) {
    if (tail == null) {
      tail = new Node<>(el);
      head = tail;
    } else {
      tail = new Node<>(tail, el, null);
      tail.prev.next = tail;
    }
    size++;
  }

  @Override
  public boolean offer(T el) {
    return add(el);
  }

  @Override
  public boolean offerFirst(T el) {
    addFirst(el);
    return true;
  }

  @Override
  public boolean offerLast(T el) {
    return add(el);
  }

  @Override
  public void push(T el) {
    addFirst(el);
  }

  // Remove operations

  @Override
  public T remove() {
    return removeFirst();
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is outside of the list");
    }
    return unlink(search(index));
  }

  @Override
  public T removeFirst() {
    final Node<T> f = head;
    if (f == null) {
      throw new NoSuchElementException();
    }

    if (f.next == null) {
      head = tail = null;
    } else {
      head = f.next;
      head.prev = null;
    }

    size--;
    return f.destruct();
  }

  @Override
  public T removeLast() {
    final Node<T> l = tail;
    if (l == null) {
      throw new NoSuchElementException();
    }

    if (l.prev == null) {
      head = tail = null;
    } else {
      tail = l.prev;
      tail.next = null;
    }

    size--;
    return l.destruct();
  }

  @Override
  public T poll() {
    return pollFirst();
  }

  @Override
  public T pollFirst() {
    return head == null ? null : removeFirst();
  }

  @Override
  public T pollLast() {
    return tail == null ? null : removeLast();
  }

  @Override
  public T pop() {
    return removeFirst();
  }

  // Get operations

  @Override
  public T element() {
    return getFirst();
  }

  @Override
  public T getFirst() {
    if (head == null) {
      throw new NoSuchElementException();
    }
    return head.item;
  }

  @Override
  public T getLast() {
    if (tail == null) {
      throw new NoSuchElementException();
    }
    return tail.item;
  }

  @Override
  public T peek() {
    return peekFirst();
  }

  @Override
  public T peekFirst() {
    return head == null ? null : head.item;
  }

  @Override
  public T peekLast() {
    return tail == null ? null : tail.item;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is outside of the list");
    }
    return search(index).item;
  }

  /**
   * Find node by index.
   * Make sure that index is in bounds of deque.
   */
  private Node<T> search(int index) {
    assert index >= 0 && index < size;

    Node<T> node;

    if (index < size / 2) {
      node = head;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
    } else {
      node = tail;
      for (int i = size - 1; i > index; i--) {
        node = node.prev;
      }
    }

    return node;
  }

  // Other

  @Override
  public int size() {
    return this.size;
  }

  private class DoubleLinkedListIterator implements ListIterator<T> {
    private Node<T> lastReturned;
    private Node<T> next;

    private int index; // Index of next element

    DoubleLinkedListIterator(int index) {
      next = index == size ? null : search(index);
      this.index = index;
    }

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      assert next != null;

      lastReturned = next;
      next = lastReturned.next;
      index++;

      return lastReturned.item;
    }

    @Override
    public boolean hasPrevious() {
      return index > 0;
    }

    @Override
    public T previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }

      // next() can set next to null
      lastReturned = next = (next == null) ? tail : next.prev;
      index--;

      return lastReturned.item;
    }

    @Override
    public int nextIndex() {
      return index;
    }

    @Override
    public int previousIndex() {
      return index - 1;
    }

    @Override
    public void remove() {
      if (lastReturned == null) {
        throw new IllegalStateException();
      }

      if (lastReturned == next) {
        next = lastReturned.next;
      } else {
        index--;
      }

      unlink(lastReturned);
      lastReturned = null;
    }

    @Override
    public void set(T el) {
      if (lastReturned == null) {
        throw new IllegalStateException();
      }

      lastReturned.item = el;
    }

    @Override
    public void add(T el) {
      lastReturned = null;

      if (next == null) {
        addLast(el);
      } else {
        insertBefore(el, next);
      }

      index++; // size changed
    }

    private void insertBefore(T el, Node<T> node) {
      final Node<T> newNode = new Node<>(node.prev, el, node);

      if (node.prev == null) {
        head = newNode;
      } else {
        node.prev.next = newNode;
      }
      node.prev = newNode;

      size++;
    }
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Can not start iterator from last or out of bounds index");
    }
    return new DoubleLinkedListIterator(index);
  }

  // Not implemented

  @Override
  public Iterator<T> descendingIterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeFirstOccurrence(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeLastOccurrence(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DoubleLinkedList<?> other)) {
      return false;
    }
    if (this.size != other.size) {
      return false;
    }

    Iterator<T> thisIterator = this.iterator();
    Iterator<?> otherIterator = other.iterator();

    while (thisIterator.hasNext() && otherIterator.hasNext()) {
      T thisElement = thisIterator.next();
      Object otherElement = otherIterator.next();

      if (!thisElement.equals(otherElement)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hash = 1;
    for (T el : this) {
      hash *= el.hashCode();
    }
    return hash;
  }
}
