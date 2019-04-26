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
        for (int i = idx; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iteratorIndex;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = iteratorIndex; i < size; i++) {
                    if (array[i] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public T next() {
                return (T) array[iteratorIndex++];
            }
        };
    }
}
