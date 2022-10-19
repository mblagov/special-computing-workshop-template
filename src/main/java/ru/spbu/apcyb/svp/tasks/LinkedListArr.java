package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Связный список на основе массива.
 */
public class LinkedListArr implements List<Object> {

  private Object[] dataArray;
  private int size;
  private int capacity;


  /**
   * Связный список на основе массива.
   */
  public LinkedListArr() {
    int defaultCapacity = 10;
    dataArray = new Object[defaultCapacity];
    capacity = dataArray.length;
    size = 0;
  }

  /**
  * Расширение массива.
  */
  public boolean resize() {
    long newCapacityLong = (capacity * 3L) / 2L + 1;
    int newCapacity = (int) newCapacityLong;
    dataArray = Arrays.copyOf(dataArray, newCapacity);
    capacity = newCapacity;
    return true;
  }

  /**
   * Добавление в начало aka push.
   */
  public void addFirst(Object o) {
    System.arraycopy(dataArray, 0, dataArray, 1, size);
    dataArray[0] = o;
    size += 1;
  }

  /**
  * Удаление первого элемента aka pop.
  */
  public Object removeFirst() {
    return remove(0);
  }

  /**
  * Взятие первого элемнта aka peek.
  */
  public Object getFirst() {
    return get(0);
  }

  @Override
  public int size() {
    return size;
  }

  /**
  * Проверка наличия по значению.
  */
  @Override
  public boolean contains(Object value) {
    boolean res = false;
    for (Object o : dataArray) {
      if (o == value) {
        res = true;
        break;
      }
    }
    return res;
  }

  /**
  * Проверка пустоты.
  */
  @Override
   public boolean isEmpty() {
    return (size == 0);
  }

  /**
  * Получение по индексу.
  */
  @Override
    public Object get(int index) {
    return dataArray[index];
  }

  /**
  * Добавление в конкретную позицию.
  */
  @Override
    public void add(int index, Object newValue) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Некуда добавлять или неправильный индекс.");
    }
    if (size >= capacity) {
      boolean resizeResult = resize();
      if (!resizeResult) {
        throw new ArrayStoreException("Массив не увеличился.");
      }
    }
    System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
    dataArray[index] = newValue;
    size += 1;

  }

  /**
   * Добавление в конец.
   */
  @Override
  public boolean add(Object newValue) {
    if (size >= capacity) {
      boolean resizeResult = resize();
      if (!resizeResult) {
        throw new ArrayStoreException("Массив не увеличился.");
      }
    }
    dataArray[size] = newValue;
    size += 1;
    return true;
  }

  /**
   * Удаление по индексу.
   */
  @Override
  public Object remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Некуда добавлять или неправильный индекс.");
    }
    Object temp = dataArray[index];
    System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
    size -= 1;
    return temp;
  }


  @Override
    public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public boolean addAll(int index, Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public Object set(int index, Object element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }


  @Override
    public int indexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
    public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }
}
