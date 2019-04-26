package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private int[] array;
    private int index = -1;

    public EvenIterator(int[] array) {
        this.array = array;
        hasNext();
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index + 1; i != array.length; i++) {
            if (isEven(array[i])) {
                result = true;
                index = i - 1;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[++index];
    }

    private boolean isEven(int number) {

        return number % 2 == 0;
    }
}
