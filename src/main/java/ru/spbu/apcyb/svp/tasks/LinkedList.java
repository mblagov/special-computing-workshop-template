package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Реализация двусвязного линейного списка.
 */

public class LinkedList implements List<Object> {

  private Node head;
  private Node tail;

  public LinkedList() {
    head = null;
    tail = null;
  }

  @Override
  public boolean add(Object data) { // Добавление в конец
    Node temp = new Node(data);

    if (isEmpty()) {
      head = temp;
    } else {
      tail.next = temp;
    }

    temp.prev = tail;
    tail = temp;

    return true;
  }

  @Override
  public void add(int index, Object data) { // Добавление в конкретную позицию
    Node curr = head;
    int c = 0;

    while (curr != null && c != index) {
      curr = curr.next;
      c++;
    }

    Node temp = new Node(data);

    curr.prev.next = temp;
    temp.prev = curr.prev;
    curr.prev = temp;
    temp.next = curr;
  }

  /**
   * Метод удаляет первый элемент в двусвязном списке.
   */
  public Object removeFirst() {
    Object temp;
    if (head.next == null) {
      tail = null;
    } else {
      head.next.prev = null;
    }

    temp = head.data;
    head = head.next;

    return temp;
  }

  /**
   * Метод удаляет последний элемент в двусвязном списке.
   */
  public Object removeLast() {
    Object temp;
    if (tail.prev == null) {
      head = null;
    } else {
      tail.prev.next = null;
    }

    temp = tail.data;
    tail = tail.prev;

    return temp;
  }


  @Override
  public boolean remove(Object data) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object remove(int index) { // Удаление по индексу
    Node curr = head;
    Object temp;
    int c = 0;

    while (curr != null && c != index) {
      curr = curr.next;
      c++;
    }

    if (curr == head) {
      temp = removeFirst();
    } else if (curr == tail) {
      temp = removeLast();
    } else {
      temp = curr.data;
      curr.prev.next = curr.next;
      curr.next = curr.prev;
    }

    return temp;
  }

  @Override
  public boolean contains(Object data) { // Проверка наличия по значению
    Node curr = head;
    boolean temp = false;
    while (curr != null) {
      if (curr.data.equals(data)) {
        temp = true;
        break;
      }
      curr = curr.next;
    }
    return temp;
  }

  @Override
  public boolean isEmpty() { // Проверка на пустоту
    return head == null;
  }

  @Override
  public Object get(int index) { // Получение по индексу
    Node curr = head;
    int c = 0;

    while (curr != null && c != index) {
      curr = curr.next;
      c++;
    }

    return curr.data;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node temp = head;
    sb.append(temp.data);
    temp = temp.next;
    while (temp != null) {
      sb.append(", ").append(temp.data);
      temp = temp.next;
    }
    return sb.toString();
  }

  @Override
  public int size() {
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
  public boolean containsAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object set(int index, Object element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator listIterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator listIterator(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }
}
