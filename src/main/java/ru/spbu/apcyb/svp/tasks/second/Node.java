package ru.spbu.apcyb.svp.tasks.second;

/**
 * Node is auxiliary class for store value and manipulate of {@link DoubleLinkedList} cell.
 * It's final class for using only within {@link DoubleLinkedList}
 *
 * @param <T> is type of value inside node
 */
final class Node<T> {
    private T item;

    private Node<T> next;
    private Node<T> prev;

    /**
     * Create Node and link to previous and next.
     *
     * @param prev    previous {@link Node} (can be null)
     * @param element inner value
     * @param next    next {@link Node} (can be null)
     */
    Node(Node<T> prev, T element, Node<T> next) {
        this.prev = prev;
        this.item = element;
        this.next = next;

        if (next != null) {
            next.prev = this;
        }

        if (prev != null) {
            prev.next = this;
        }
    }

    /**
     * Shortcut for {@code Node(null, element, null)}. Create unlinked {@link Node}.
     *
     * @param element inner value
     */
    Node(T element) {
        this(null, element, null);
    }

    /**
     * Returns item and set all fields to null. Updates links
     */
    T destruct() {
        final T el = item;

        if (prev != null) {
            prev.next = next;
        }

        if (next != null) {
            next.prev = prev;
        }

        prev = null;
        next = null;
        item = null;

        return el;
    }

    /**
     * Getter for {@code item}
     */
    T getItem() {
        return item;
    }


    /**
     * Setter for {@code item}
     */
    void setItem(T element) {
        item = element;
    }

    /**
     * Getter for {@code next}
     */
    Node<T> getNext() {
        return next;
    }

    /**
     * Getter for {@code prev}
     */
    Node<T> getPrev() {
        return prev;
    }
}