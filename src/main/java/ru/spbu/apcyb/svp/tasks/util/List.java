package ru.spbu.apcyb.svp.tasks.util;

public class List<T> {
    private T arr[];
    private int capacity; //кол-во свободных мест
    private int current; //текущее кол-во элементов

    public void ListClass()
    {
        //arr = new T[1];
        capacity = 1;
        current = 0;
    }

    public void add(T element) {//добавление в конец
        if (current == capacity) {
            T[] temp = (T[])new Object[capacity];

            for (int i = 0; i < capacity; i++) {
                temp[i] = arr[i];
            }

            capacity *= 2;
            arr = temp;
        }

        // Inserting data
        arr[current] = element;
        current++;
    }

    public void remove(int index) {//удаление по индексу

    }

    public boolean contains(T element) {//проверка наличия
        return false;
    }

    public boolean isEmpty() { //проверка пустоты списка
        return false;
    }

    public void get(int index) { //получение по индексу

    }

    public void add(int index, T element) { //добавление в конкретную позицию

    }

}

