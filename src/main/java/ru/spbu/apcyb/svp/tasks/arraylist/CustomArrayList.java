package ru.spbu.apcyb.svp.tasks.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * Кастомный класс ArrayList.
 *
 * @param <T> - generic
 */
public class CustomArrayList<T> implements List<T> {

  private static final int CAPACITY = 8;
  private Object[] array;
  private int size;
  private static final String ERROR_DESCRIPTION = "That operation is not supported yet";
  public static final Logger logger = Logger.getLogger(CustomArrayList.class.getName());

  /**
   * Создание объекта класса MyArrayList.
   */
  public CustomArrayList() {
    this.array = new Object[CAPACITY];
    this.size = 0;
  }

  /**
   * Создание объекта класса MyArrayList.
   *
   * @param arrayBase - массив на основе которого создаётся линейный список
   */
  public CustomArrayList(T[] arrayBase) {
    this.array = Arrays.copyOf(arrayBase, arrayBase.length);
    this.size = arrayBase.length;
  }

  /**
   * Метод, который проверяет, чтобы индекс не был за границей массива.
   *
   * @param index - индекс
   */
  public void checkBoundaries(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index outside the list boundaries");
    }
  }

  /**
   * Метод, который увеличивает размер массива.
   */
  public void resize() {
    Object[] newArray = new Object[(CAPACITY * 3) / 2 + 1];
    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
    this.array = newArray;
  }

  /**
   * Метод, который добавляет эл-т в конец списка.
   *
   * @param object element whose presence in this collection is to be ensured
   * @return - эл-т добавлен
   */
  @Override
  public boolean add(T object) {
    if (size >= CAPACITY) {
      resize();
    }
    this.array[size] = object;
    size++;
    return true;
  }

  /**
   * Метод, который добавляет заданный эл-т по заданному индексу.
   *
   * @param index   index at which the specified element is to be inserted
   * @param element element to be inserted
   */
  @Override
  public void add(int index, T element) {
    checkBoundaries(index);
    if (size >= CAPACITY) {
      resize();
    }
    System.arraycopy(this.array, index, this.array, index + 1, size - index);
    this.array[index] = element;
    size += 1;
  }

  /**
   * Метод, который удаляет эл-т по индексу.
   *
   * @param index the index of the element to be removed
   * @return - эл-т, который удалили
   */
  @Override
  @SuppressWarnings("unchecked")
  public T remove(int index) {
    checkBoundaries(index);
    Object[] newArray = Arrays.copyOf(this.array, this.array.length);
    T element = (T) newArray[index];
    System.arraycopy(this.array, index + 1, newArray, index, size - index);
    size -= 1;
    return element;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  /**
   * Метод, который ищет заданный эл-т.
   *
   * @param element element whose presence in this list is to be tested
   * @return - true в случае, если такой эл-т есть, false - нет
   */
  @Override
  public boolean contains(Object element) {
    for (Object o : this.array) {
      if (o == element) {
        return true;
      }
    }
    return false;
  }

  /**
   * Проверка на пустоту.
   *
   * @return - пуст или нет
   */
  @Override
  public boolean isEmpty() {
    return this.array.length == 0;
  }

  /**
   * Метод, который получает эл-т по индексу.
   *
   * @param index index of the element to return
   * @return -  эл-т с заданным индексом
   */
  @Override
  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkBoundaries(index);
    return (T) this.array[index];
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public T set(int index, T element) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public ListIterator<T> listIterator() {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException(ERROR_DESCRIPTION);
  }
}
