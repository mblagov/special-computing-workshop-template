package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Линейный список
 */

public class LinkedList implements List<Object> {

    private Object[] data;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public LinkedList() {
        data = new Object[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Добавление в конец
     */
    @Override
    public boolean add(Object element) {
        if (size == data.length)
            increaseSize();
        data[size] = element;
        size++;
        return true;
    }

    /**
     * Удаление по индексу
     */
    @Override
    public Object remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Неправильный индекс!");
        }
        Object temp = data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        size -= 1;
        return temp;
    }

    /**
     * Проверка наличия
     */
    @Override
    public boolean contains(Object value) {
        for (Object o : data) {
            if (o == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка пустоты списка
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
        return data[index];
    }

    /**
     * Добавление в конкретную позицию.
     */
    @Override
    public void add(int index, Object element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Неправильный индекс!");
        }
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length)
            increaseSize();
        ArrayList<Object> tempList = new ArrayList<>(Arrays.asList(data));
        tempList.add(index, element);
        data = tempList.toArray();
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Получение индекса по значению
     */

    @Override
    public int indexOf(Object element) {
        int counter = 0;
        if (!contains(element)) {
            return -1;
        }
        for(int i = 0; i < size(); i++) {
            if (element == data[i]) {
                counter = i;
                break;
            }
        }
        return counter;
    }

    /**
     * Расширение массива.
     */
    public void increaseSize() {
        data = Arrays.copyOf(data, (int) (data.length + (data.length * 3L) / 2L + 1));
    }

    /*
     * push
     */

    public void addFirst(Object o) {
        System.arraycopy(data, 0, data, 1, size);
        data[0] = o;
        size += 1;
    }

    /*
     * pop
     */

    public Object removeFirst() {
        return remove(0);
    }

    /*
     * peek
     */

    public Object getFirst() {
        return get(0);
    }

    @Override
    public Iterator<Object> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public Object[] toArray() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public boolean remove(Object o) throws UnsupportedOperationException {
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