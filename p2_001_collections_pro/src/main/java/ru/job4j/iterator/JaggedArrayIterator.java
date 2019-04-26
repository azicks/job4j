package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator {
    private int[][] matrix;
    private int majorIndex = -1;
    private int minorIndex = -1;

    public JaggedArrayIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return majorIndex + 1 < matrix.length || minorIndex + 1 < matrix[majorIndex].length;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            if (majorIndex == -1) {
                majorIndex++;
            }
            if (minorIndex + 1 == matrix[majorIndex].length) {
                majorIndex++;
                minorIndex = 0;
            } else {
                minorIndex++;
            }
        } else {
            throw new NoSuchElementException();
        }
        return matrix[majorIndex][minorIndex];
    }
}
