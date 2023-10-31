package ru.spbu.apcyb.svp.tasks;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class MyArrayList implements List {
    private final int DEFAULT_CAPACITY = 2;
    private int size;
    private Object[] data;

    public MyArrayList() {
        this.size = 0;
        this.data = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.size = 0;
            this.data = new Object[capacity];
        } else throw new IllegalArgumentException("Capacity must be positive integer, provided: " + capacity);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Capacity: ").append(this.data.length).append("\n");
        res.append("Size: ").append(this.size).append("\n");
        for (int i = 0; i < this.size; i++) {
            res.append(this.data[i].toString()).append(" ");
        }
        res.append("\n");
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MyArrayList) {
            if (this.size == ((MyArrayList) o).size()) {
                for (int i = 0; i < this.size; i++) {
                    if (!this.get(i).equals(((MyArrayList) o).get(i))) return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(Object o) {
        if (this.size == this.data.length) this.data = Arrays.copyOf(this.data, this.size * 2);
        this.data[size++] = o;
        return true;
    }

    @Override
    public void add(int index, Object o) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException("Element index must be equals to or more than 0 " +
                    "and equals to or less than size, current size: " + this.size + ", index provided: " + index);
        if (index == this.size) this.add(o);
        else {
            if (this.size == this.data.length) this.data = Arrays.copyOf(this.data, this.size * 2);
            for (int i = this.size; i > index; i--) {
                this.data[i] = this.data[i - 1];
            }
            this.data[index] = o;
            ++this.size;
        }
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {
        Object o = this.get(index);
        for (int i = index; i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        this.data[--this.size] = null;
        return o;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException("Element index must be equals to or more than 0 " +
                    "and less than size, current size: " + this.size + ", index provided: " + index);
        return this.data[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(o)) return true;
        }
        return false;
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

    public boolean remove(Object o) {
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
}
