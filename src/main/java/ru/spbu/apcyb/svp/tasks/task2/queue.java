package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class queue implements Queue<Object> {

    private final linkedlist queue;

    public queue() {
        this.queue = new linkedlist();
    }

    @Override
    public int size() { // размер очереди
        return this.queue.size();
    }

    @Override
    public boolean add(Object obj) { // постановка в очередь
        return this.queue.add(obj);
    }

    @Override
    public Object peek() { // получение первого в очереди
        Object result = null;
        if (!this.isEmpty()) {
            result = this.queue.get(0);
        }
        return result;
    }

    @Override
    public boolean isEmpty() { // проверка на пустоту
        return this.queue.isEmpty();
    }

    @Override
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException("Метод contains не переопределен для очереди");
    }

    @Override
    public Iterator<Object> iterator() {
        throw new UnsupportedOperationException("Метод iterator не переопределен для очереди");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Метод toArray не переопределен для очереди");
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        throw new UnsupportedOperationException("Метод toArray не переопределен для очереди");
    }

    @Override
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Метод remove не переопределен для очереди");
    }

    @Override
    public boolean containsAll(Collection<?> col) {
        throw new UnsupportedOperationException("Метод containsAll не переопределен для очереди");
    }

    @Override
    public boolean addAll(Collection<?> col) {
        throw new UnsupportedOperationException("Метод addAll не переопределен для очереди");
    }

    @Override
    public boolean removeAll(Collection<?> col) {
        throw new UnsupportedOperationException("Метод removeAll не переопределен для очереди");
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        throw new UnsupportedOperationException("Метод retainAll не переопределен для очереди");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Метод clear не переопределен для очереди");
    }

    @Override
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Метод offer не переопределен для очереди");
    }

    @Override
    public Object remove() {
        throw new UnsupportedOperationException("Метод remove не переопределен для очереди");
    }

    @Override
    public Object poll() {
        throw new UnsupportedOperationException("Метод poll не переопределен для очереди");
    }

    @Override
    public Object element() {
        throw new UnsupportedOperationException("Метод element не переопределен для очереди");
    }

}