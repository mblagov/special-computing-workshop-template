package ru.spbu.apcyb.svp.tasks.second;

final class Node<T> {
    T item;
    Node<T> next;
    Node<T> prev;

    Node(Node<T> prev, T element, Node<T> next) {
        this.prev = prev;
        this.item = element;
        this.next = next;
    }

    Node(T element) {
        this(null, element, null);
    }

    /**
     * Returns item and set all fields to null.
     */
    T destruct() {
        final T el = item;

        prev = null;
        next = null;
        item = null;

        return el;
    }
}