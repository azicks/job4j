package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ArrList<E> implements Iterable<E> {
    private Object[] container;
    private int size;
    private int modCount;

    public ArrList() {
        container = new Object[10];
    }

    public ArrList(int size) {
        container = new Object[size];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentModCount = modCount;
            private int position;
            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(position++);
            }
        };
    }

    public void add(E value) {
        checkSize();
        container[size++] = value;
        modCount++;
    }

    public E get(int index) {
        return (E) container[index];
    }

    private void checkSize() {
        if (container.length == size) {
            Object[] arr = new Object[size + size / 2];
            System.arraycopy(container, 0, arr, 0, container.length);
            container = arr;
        }
    }
}
