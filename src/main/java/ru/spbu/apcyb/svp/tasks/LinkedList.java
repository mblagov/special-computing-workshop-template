package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class LinkedList<E> implements List<E> {

  private Node<E> startPos;
  private Node<E> endPos;
  private int size;


  public LinkedList() {
    startPos = null;
    endPos = null;
    size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return startPos == null;
  }


  @Override
  public int indexOf(Object o) {
    if (o == null) {
      return -1;
    }

    int index = 0;

    for (Node<E> currentNode = startPos; currentNode != null; currentNode = currentNode.next) {
      if (o.equals(currentNode.item)) {
        return index;
      }
      index++;
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }


  @Override
  public boolean add(E item) {
    if (item == null) {
      return false;
    }
    Node<E> addNode = new Node<>(null, item, null);
    if (isEmpty()) {
      startPos = addNode;
    } else if (endPos == null) {
      endPos = addNode;
      endPos.prev = startPos;
      startPos.next = endPos;
    } else {
      endPos.next = addNode;
      addNode.prev = endPos;
      endPos = addNode;
    }
    size += 1;
    return true;
  }


  public boolean add(E item, int index) {
    if (item == null || index < 0 || index > size) {
      return false;
    }
    Node<E> addNode = new Node<>(null, item, null);
    if (isEmpty()) {
      startPos = addNode;
    } else if (index == size) {
      this.add(item);
    } else {
      Node<E> currNode = startPos;
      int posCounter = 0;

      while (posCounter != index) {
        currNode = currNode.next;
        posCounter += 1;
      }

      currNode.prev.next = addNode;
      addNode.prev = currNode.prev;
      currNode.prev = addNode;
      addNode.next = currNode;
    }

    size += 1;
    return true;
  }

  @Override
  public void add(int index, E element) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }


  @Override
  public E remove(int index) {
    if (index < 0 || index > size - 1) {
      return null;
    }

    Node<E> currNode = startPos;
    int posCounter = 0;

    while (posCounter != index) {
      currNode = currNode.next;
      posCounter += 1;
    }

    if (currNode == startPos) {

      if (startPos.next == null) {
        endPos = null;
      } else {
        startPos.next.prev = null;
      }

      startPos = startPos.next;

      return currNode.item;
    }

    if (currNode == endPos) {

      if (startPos.next == null) {
        startPos = null;
      } else {
        endPos.prev.next = null;
      }

      endPos = endPos.prev;

      return currNode.item;
    }

    currNode.prev.next = currNode.next;
    currNode.next.prev = currNode.prev;
    size -= 1;

    return currNode.item;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }


  @Override
  public E get(int index) {
    if (index < 0 || index > size - 1) {
      return null;
    }

    Node<E> currNode = startPos;
    int posCounter = 0;

    while (posCounter != index) {
      currNode = currNode.next;
      posCounter += 1;
    }

    return currNode.item;
  }

  @Override
  public Iterator<E> iterator() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public E set(int index, E element) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public ListIterator<E> listIterator() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  private static class Node<E> {

    E item;
    Node<E> prev;
    Node<E> next;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
}
