package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


class DoublyLinkedLinearList implements List {

    private Node head;
    private Node tail;

    private int size;

    static class Node {
        private final Object value;
        private Node previous;
        private Node next;

        Node(Object val, Node prevVal, Node nextVal) {
            this.value = val;
            this.previous = prevVal;
            this.next = nextVal;
        }
    }

    public DoublyLinkedLinearList() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        int index = this.indexOf(o);
        return index != -1;
    }

    @Override
    public int indexOf(Object o) {
        Node cur = this.head;
        for (int i = 0; i < size; i++) {
            if (cur.value.equals(o)) {
                return i;
            }
            cur = cur.next;
        }
        return -1;
    }

    @Override
    public boolean add(Object o) {
        if (isEmpty()) {
            head = new Node(o, null, null);
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            Node node = new Node(o, cur, null);
            cur.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, Object o) {
        indexBoundsCheck(index);

        if (isEmpty()) {
            head = new Node(o, null, null);
        } else if (index == 0) {
            Node newNode = new Node(o, null, head);
            head.previous = newNode;
            head = newNode;
        } else if (index == size) {
            add(o);
            size--;
        } else {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            Node newNode = new Node(o, cur.previous, cur);
            cur.previous.next = newNode;
            cur.previous = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        isEmptyCheck();
        indexBoundsCheck(index);
        Object o;
        if (index == 0) {
            o = head.value;
            head = head.next;
        } else if (index + 1 == size) {
            o = tail.value;
            remove(o);
            size++;
        } else {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }

            o = cur.value;
            cur.previous.next = cur.next;
            cur.next.previous = cur.previous;
        }
        size--;

        return o;
    }

    @Override
    public boolean remove(Object o) {
        isEmptyCheck();
        if (!contains(o)) {
            throw new NoSuchElementException("List doesn't contains object " + o.toString());
        }
        if (head.value.equals(o)) {
            head = head.next;
        }
        if (tail.value.equals(o)) {
            tail = tail.previous;
        } else {
            Node cur = head;
            while (cur != null && !cur.value.equals(o)) {
                cur = cur.next;
            }
            if (cur != null && cur.next != null) {
                cur.next.previous = cur.previous;
            }
        }
        size--;
        return true;

    }

    @Override
    public Object get(int index) {
        isEmptyCheck();
        indexBoundsCheck(index);

        Object o;
        if (index == 0) {
            o = head.value;
        } else if (index == size) {
            o = tail.value;
        } else {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            o = cur.value;
        }
        return o;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    public void isEmptyCheck() {
        if (isEmpty()) {
            throw new NullPointerException("List is null");
        }
    }
    public void indexBoundsCheck(int index) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + "is out of bounds");
        }
    }
}
