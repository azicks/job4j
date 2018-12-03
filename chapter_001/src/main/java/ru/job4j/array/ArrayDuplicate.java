package ru.job4j.array;

import java.util.Arrays;

/**
 * @since 03.12.2018
 */
public class ArrayDuplicate {

    public String[] remove(String[] array) {

        int numOfDuplicates = 0;
        int maxIndex = array.length - 1;

        for (int back = maxIndex; back > 0; back--) {
            for (int idx = 0; idx < back; idx++) {
                if (array[idx].equals(array[back])) {
                    array[idx] = array[maxIndex - numOfDuplicates];
                    numOfDuplicates++;
                    break;
                }
            }
        }
        return Arrays.copyOf(array, array.length - numOfDuplicates);
    }
}
