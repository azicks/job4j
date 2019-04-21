package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private int[] array;
    private int index = 0;

    public EvenIterator(int[] array) {
        this.array = array;
        getNext();
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index != -1) {
            for (int i = index; i != array.length; i++) {
                if (isEven(array[i])) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        int i = index++;
        getNext();
        return array[i];
    }

    private void getNext() {
        if (index != -1) {
            int even = -1;
            for (int i = index; i != array.length; i++) {
                if (isEven(array[i])) {
                    even = i;
                    break;
                }
            }
            index = even;
        }
    }

    private boolean isEven(int number) {

        return number % 2 == 0;
    }
}
