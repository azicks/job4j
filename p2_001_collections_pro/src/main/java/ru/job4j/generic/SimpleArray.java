package ru.job4j.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] array;
    private int index;

    public SimpleArray(int cells) {
        this.array = new Object[cells];
    }

    public void add(T element) {
        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index++] = element;
    }

    public void set(int idx, T element) {
        if (idx >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[idx] = element;
    }

    public T get(int idx) {
        if (idx >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[idx];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iteratorIndex;

            @Override
            public boolean hasNext() {
                return iteratorIndex < array.length;
            }

            @Override
            public T next() {
                return (T) array[iteratorIndex++];
            }
        };
    }
}
