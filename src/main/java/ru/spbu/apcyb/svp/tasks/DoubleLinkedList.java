package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class DoubleLinkedList implements List<Object> {
  private Element head;
  private Element tail;
  
  public DoubleLinkedList() {
    head = null;
    tail = null;
  }
  
  //Добавление элемента в конец
  @Override
  public boolean add(Object obj) {
  
    Element newElem = new Element(obj);
    
    if (isEmpty()) {
      newElem.prev = null;
      newElem.next = null;
      
      head = newElem;
    } else {
      tail.next = newElem;
      newElem.prev = tail;
    }
    
    tail = newElem;
  
    return true;
  }
  
  //Добавление элемента на n место
  @Override
  public void add(int n, Object obj) {
  
    Element newElem = new Element(obj);
    
    if (head == null) {
      newElem.prev = null;
      newElem.next = null;
  
      head = newElem;
      tail = newElem;
    } else {
  
      Element helper = head;
      
      for (int i = 0; i < n; i++) {
        
        if (helper.next == null) {
          break;
        }
        
        helper = helper.next;
      }
  
      if (helper != head) {
        helper.prev.next = newElem;
      }
      
      newElem.prev = helper.prev;
      helper.prev = newElem;
      newElem.next = helper;
      
    }
  }
  
  //Удаление  элемента на n месте
  @Override
  public Object remove(int n) {
    
    if (head == null) {
      return null;
    }
    
    if (n == 0) {
      return removeFirst();
    }
    
    Element helper = head;
  
    for (int i = 0; i < n; i++) {
    
      if (helper.next == null) {
        throw new ArrayIndexOutOfBoundsException("Элементов меньше, чем заданное число!");
      }
    
      helper = helper.next;
    }
    
    helper.prev.next = helper.next;
    helper.next = helper.prev;
    
    return helper.obj;
  }
  
  @Override
  public boolean remove(Object data) {
    throw new UnsupportedOperationException();
  }
  
  //Удаление первого элемента (головы) из списка
  public Object removeFirst() {
    
    Object obj;
  
    if (head == null) {
      return null;
    } else if (head.next == null) {
      tail = null;
    } else {
      head.next.prev = null;
    }
    
    obj = head.obj;
    head = head.next;
    
    return obj;
  }
  
  //Удаление последнего элемента (головы) из списка
  public Object removeLast() {
    
    Object obj;
    
    if (head == null) {
      return null;
    } else if (tail.prev == null) {
      head = null;
    } else {
      tail.prev.next = null;
    }
  
    obj = tail.obj;
    tail = tail.prev;
    
    return obj;
  }
  
  //Проверка наличия элемента по значению
  @Override
  public boolean contains(Object obj) {
    
    if (obj != null) {
      
      Element helper = head;
  
      while (helper != null) {
  
        if (helper.obj.equals(obj)) {
          return true;
        }
  
        helper = helper.next;
      }
    }
    
    return false;
  }
  
  //Проверка на null
  @Override
  public boolean isEmpty() {
    return (head == null);
  }
  
  //Получение n'ого объекта
  @Override
  public Object get(int n) {
    
    if (n < 0) {
      return null;
    }
    
    Element helper = head;
    
    for (int i = 0; i < n; i++) {
      
      if (helper.next == null) {
        throw new ArrayIndexOutOfBoundsException("Элементов меньше, чем заданное число!");
      }
      
      helper = helper.next;
    }
    
    return helper.obj;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    Element helper = head;
    sb.append(helper.obj);
    helper = helper.next;
    
    while (helper != null) {
      sb.append(", ").append(helper.obj);
      helper = helper.next;
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
