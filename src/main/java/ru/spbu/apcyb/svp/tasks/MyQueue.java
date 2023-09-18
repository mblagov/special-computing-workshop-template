package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;

class MyQueue implements java.util.Queue<Object> {
  
  private final DoubleLinkedList queue;
  
  public MyQueue() {
    queue = new DoubleLinkedList();
  }
  
  //Добавление элемента в конец очереди
  @Override
  public boolean add(Object obj) {
    
    queue.add(obj);
    return true;
  }
  
  //Получение первого элемента
  @Override
  public Object peek() {
    
    return queue.get(0);
  }
  
  //Проверка на пустоту
  @Override
  public boolean isEmpty() {
    
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