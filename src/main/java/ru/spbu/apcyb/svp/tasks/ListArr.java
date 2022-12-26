package ru.spbu.apcyb.svp.tasks;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ListArr implements List<Object> {
  private Object[] dataArr;
  private int size;
  private int cap;

  /**
   * Связный список на основе массива.
   */
  public ListArr() {
    int defCap = 10;
    dataArr = new Object[defCap];
    cap = dataArr.length;
    size = 0;
  }
  public boolean resize() {
    long newcapLong = (cap * 3L) / 2L + 1;
    int newcap = (int) newcapLong;
    dataArr = Arrays.copyOf(dataArr, newcap);
    cap = newcap;
    return true;
  }

  /** 1. Добавление в конец */
  @Override
  public boolean add(Object Value) {
    if (size >= cap) {
      boolean resizeRes = resize();
      if (!resizeRes) {
        throw new ArrayStoreException("Массив не увеличился");
      }
    }
    dataArr[size] = Value;
    size += 1;
    return true;
  }


  /** 2. Удаление по индексу */
  @Override
  public Object remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Некуда добавлять или неправильный индекс");
    }
    Object temp = dataArr[index];
    System.arraycopy(dataArr, index + 1, dataArr, index, size - index);
    size -= 1;
    return temp;
  }

  /** 3. Проверка наличия по значению */
  @Override
  public boolean contains(Object value) {
    boolean res = false;
    for (Object t : dataArr) {
      if (t == value) {
        res = true;
        break;
      }
    }
    return res;
  }

  /** 4. Проверка пустоты списка */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  /** 5. Получение по индексу */
  @Override
  public Object get(int index) {
    return dataArr[index];
  }

  /** 6. Добавление в конкретную позицию */
  @Override
  public void add(int index, Object Value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Некуда добавлять или неправильный индекс");
    }
    if (size >= cap) {
      boolean resizeRes = resize();
      if (!resizeRes) {
        throw new ArrayStoreException("Массив не увеличился.");
      }
    }
    System.arraycopy(dataArr, index, dataArr, index + 1, size - index);
    dataArr[index] = Value;
    size += 1;

  }

  public void stackPush(Object o) {
    System.arraycopy(dataArr, 0, dataArr, 1, size);
    dataArr[0] = o;
    size += 1;
  }

  public Object stackPop() {
    return remove(0);
  }

  public Object stackPeek() {
    return get(0);
  }

  @Override
  public int size() {
    return size;
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

