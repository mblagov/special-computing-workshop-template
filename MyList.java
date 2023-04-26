package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList implements List<Object> {
  private static final String EXCEPT1 = "Данная операция не поддерживается";
  private static final String EXCEPT2 = "Индекс должен быть положительным";
  private Node head;

  private static class Node {
    Object data;
    Node left;
    Node right;

    Node(Object obj) {
      data = obj;
      left = null;
      right = null;
    }
  }

  public MyList() {
    this.head = null;
  }

  public MyList(Collection<?> c) {
    this.head = null;
    this.addAll(c);
  }

  @Override
  public boolean addAll(Collection<?> col) {
    for (Object it : col) {
      this.add(it);
    }
    return true;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int size() {
    if (isEmpty()) {
      return 0;
    } else {
      int count;
      Node current = head;
      for (count = 0; current != null; count++) {
        current = current.right;
      }
      return count;
    }
  }

  @Override
  public boolean contains(Object obj) {
    boolean inList = false;
    Node current = head;
    while ((current != null) && !inList) {
      if (current.data.equals(obj)) {
        inList = true;
      }
      current = current.right;
    }
    return inList;
  }

  @Override
  public Object get(int index) {
    Node current = head;
    if (index >= size()) {
      throw new IndexOutOfBoundsException("Размер листа:" + size() + ", а вы ввели:" + index);
    } else if (index < 0) {
      throw new IndexOutOfBoundsException(EXCEPT2);
    }
    for (int i = 0; i < index; i++) {
      current = current.right;
    }
    return current.data;
  }
  /** Добавляет элемент в конец списка */
  @Override
  public boolean add(Object obj) {
    Node added = new Node(obj);
    if (head != null) {
      Node current = head;
      while (current.right != null) {
        current = current.right;
      }
      current.right = added;
      added.left = current;
    } else {
      head = added;
    }
    return true;
  }

  private void addToNotNull(int index, Object obj) {
    Node added = new Node(obj);
    Node current = head;
    if (index == size()) {
      for (int i = 0; i < index - 1; i++) {
        current = current.right;
      }
      current.right = added;
      added.left = current;
    } else {
      for (int i = 0; i < index; i++) {
        current = current.right;
      }
      added.left = current.left;
      added.right = current;
      current.left = added;
      if (index != 0) {
        added.left.right = added;
      } else {
        head = added;
      }
    }
  }

  @Override
  public void add(int index, Object obj) {
    int s = size();
    if (index < 0) {
      throw new IndexOutOfBoundsException("Размер листа:" + s + ", а вы ввели:" + index);
    } else if (index > s) {
      throw new IndexOutOfBoundsException(EXCEPT2);
    } else {
      Node added = new Node(obj);
      if (s == 0) {
        head = added;
      } else {
        addToNotNull(index, obj);
      }
    }
  }

  @Override
  public Object remove(int index) {
    Node current = head;
    if (index >= size()) {
      throw new IndexOutOfBoundsException("Размер листа:" + size() + ", а вы ввели:" + index);
    } else if (index < 0) {
      throw new IndexOutOfBoundsException(EXCEPT2);
    } else {
      if (index == 0) {
        head = head.right;
        head.left = null;
      } else {
        for (int i = 0; i < index; i++) {
          current = current.right;
        }
        if (index != size() - 1) {
          current.right.left = current.left;
        }
        current.left.right = current.right;
      }
    }
    return current.data;
  }

  @Override
  public boolean addAll(int index, @Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public boolean containsAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public int indexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public boolean removeAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public boolean retainAll(@Nullable Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public Object set(int index, Object element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }

  @Override
  public <T> T[] toArray(@Nullable T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(EXCEPT1);
  }
}
