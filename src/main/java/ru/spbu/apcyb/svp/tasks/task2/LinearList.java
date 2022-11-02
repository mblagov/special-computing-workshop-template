package ru.spbu.apcyb.svp.tasks.task2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

public class LinearList implements java.util.List<Object>, Serializable {

  private transient Object[] data;
  private int size;

  private static final int INITIAL_SIZE = 7;

  public LinearList() {
    data = new Object[INITIAL_SIZE];
    size = 0;
  }

  /**
   * Увеличивает количество элементов скрытого массива на log текущей длинны
   */
  private void expand() {
    data = Arrays.copyOf(data, data.length + (int) Math.log(data.length) + 1);
  }


  public int getSize() {
    return size;
  }

  @Override
  public boolean add(Object elem) throws NullPointerException {
    if (elem == null) {
      throw new NullPointerException();
    }
    int i = size;
    if (i == data.length) {
      expand();
    }
    size++;
    data[i] = elem;
    return true;
  }

  @Override
  public void add(int index, Object element)
      throws IndexOutOfBoundsException, NullPointerException {
    if (index > size) {
      throw new IndexOutOfBoundsException();
    }
    if (element == null) {
      throw new NullPointerException();
    }
    ArrayList<Object> tempList = new ArrayList<>(Arrays.asList(data));
    tempList.add(index, element);
    data = tempList.toArray();
    size++;
  }

  @Override
  public Object get(int index) throws IndexOutOfBoundsException {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return data[index];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public boolean remove(Object o) {
    if (contains(o)) {
      remove(indexOf(o));
      return true;
    }
    return false;
  }

  @Override
  public Object remove(int index) throws IndexOutOfBoundsException {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }
    Object e = data[index];

    ArrayList<Object> tempList = new ArrayList<>(Arrays.asList(data));
    tempList.remove(index);
    data = tempList.toArray();
    size--;
    expand();

    return e;
  }

  @Override
  public int indexOf(Object o) {
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (o.equals(data[i])) {
        index = i;
      }
    }
    return index;
  }

  @Override
  public void clear() {
    this.data = new Object[7];
    this.size = 0;
  }

  public String toString() {
    Object[] tmp = Arrays.copyOf(this.data, this.size);
    return Arrays.toString(tmp);
  }

  @Override
  public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void replaceAll(UnaryOperator<Object> operator) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(Comparator<? super Object> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object set(int index, Object element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Spliterator<Object> spliterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
