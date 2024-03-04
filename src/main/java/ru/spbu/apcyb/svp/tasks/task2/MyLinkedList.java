package ru.spbu.apcyb.svp.tasks.task2;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public  class MyLinkedList<T> implements List<T>  {
    Node<T> head;
    Node<T> tail;

    int size = 0;


    @Override
    public boolean isEmpty() {//проверка пустоты
        return head == null;
    }

    @Override
    public T get(int index) { //получение по индексу
        Node<T> head1 = head;
        if (index < 0 || index > tail.index) {
            return null;
        } else {
            while(true) {
                if (head1.index == index) {
                    return head1.value;
                } else {
                    head1 = head1.next;
                }
            }
        }
    }

    @Override
    public boolean contains(Object o) { //Проверка наличия по значению
        Node<T> head1 = head;
        for (int i = 0; i <= tail.index; i++) {
            if (o == head1.value) {return true;}
            else {
                head1 = head1.next;
            }
        }
        return false;
    }

    @Override
    public boolean add(T o) {//добавление в конец
        if (head == null) {
            Node<T> element = new Node<>();
            element.value = o;
            head = element;
            tail = element;
            element.index = 0;
        } else {
            Node<T> element = new Node<>();
            element.value = o;
            tail.next = element;
            element.prev = tail;
            element.index = tail.index + 1;
            tail = element;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) { //добавление в конкретную позицию
        if (index < 0 || index > tail.index + 1) {
            throw new IndexOutOfBoundsException();
        } else if (index == tail.index + 1) {
            add(element);
        } else if (index == 0) {
            Node<T> oldHead = new Node<>();
            oldHead.value = head.value;
            oldHead.index = 1;
            oldHead.next = head.next;
            head.value = element;
            oldHead.prev = head;
            head.next = oldHead;
            changeIndex(oldHead.next);
            size++;
        } else {
            Node<T> head1 = head.next;
            while (head1.index != index) {
                head1 = head1.next;
            }

            Node<T> newElement = new Node<>();
            newElement.index = index;
            newElement.value = element;
            newElement.prev = head1.prev;
            newElement.next = head1;
            head1.prev.next = newElement;
            changeIndex(head1);
            size++;
        }
    }

    @Override
    public T remove(int index) {//удаление по индексу
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }else {
            Node<T> head1 = head;
            if (index == 0) {
                head = head.next;
            } else if (index == tail.index) {
                tail = tail.prev;
            } else if (index < 0 || index > tail.index) {
                throw new IndexOutOfBoundsException();
            } else {
                while (true) {
                    if (head1.index == index) {
                        Node<T> memory = head1;
                        for (int i = head1.index + 1; i <= tail.index; i++) {
                            memory = memory.next;
                            memory.index -= 1;
                        }
                        memory = head1.prev;
                        memory.next = head1.next;
                        memory = head.next;
                        memory.prev = head1.prev;
                        return null;
                    } else {
                        head1 = head1.next;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int size()  {
        return size;
    }

    private void changeIndex(Node<T> element) {
        boolean flag = false;
        while(!flag) {
            if (element.index == tail.index) {
                element.index++;
                flag = true;
            } else {
                element.index++;
                element = element.next;
            }
        }
    }

    @Override
    public Iterator iterator() {
        throw  new  UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }


    @Override
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

    private static class Node<T>{
        T value;
        Node<T> next = null;
        Node<T> prev = null;
        int index;
    }
}

