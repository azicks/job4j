package ru.job4j.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] array;
    private int size;

    public SimpleArray(int cells) {
        this.array = new Object[cells];
    }

    public void add(T element) {
        if (size >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[size++] = element;
    }

    public void set(int idx, T element) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[idx] = element;
    }

    public T get(int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[idx];
    }

    public void remove(int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int iteratorIndex;

            @Override
            public boolean hasNext() {
                return iteratorIndex < size;
            }

            @Override
            public T next() {
                return (T) array[iteratorIndex++];
            }
        };
    }
}
