package ru.job4j.iterator;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {
    private int[][] matrix;
    private int index = 0;
    private int length = 0;
    private int majorIndex = 0;
    private int minorIndex = 0;

    public JaggedArrayIterator(int[][] matrix) {
        this.matrix = matrix;
        for (int i = 0; i != matrix.length; i++) {
            this.length += matrix[i].length;
        }
    }

    @Override
    public boolean hasNext() {
        return index < length;
    }

    @Override
    public Object next() {
        int result = matrix[majorIndex][minorIndex];
        if (hasNext()) {
            if (++minorIndex == matrix[majorIndex].length) {
                majorIndex++;
                minorIndex = 0;
            }
        }
        index++;
        return result;
    }
}
