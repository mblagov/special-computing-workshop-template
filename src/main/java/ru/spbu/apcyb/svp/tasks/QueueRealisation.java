package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class QueueRealisation implements Queue {
    private final DoublyLinkedLinearList queue;

    QueueRealisation() {
        queue = new DoublyLinkedLinearList();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public boolean add(Object o) {
        return queue.add(o);
    }

    @Override
    public Object remove() {
        return queue.remove(0);
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public Object poll() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    @Override
    public Object element() {
        return queue.get(0);
    }

    @Override
    public Object peek() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.get(0);
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
    public void clear() {
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

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean offer(Object o) {
        throw new UnsupportedOperationException("This method is not supported");
    }




}
