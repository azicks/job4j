package ru.job4j.array;

/**
 * @since 03.12.2018
 */
public class Turn {

    public int[] back(int[] array) {

        int temp;
        int last = array.length - 1;

        for (int idx = 0; idx != array.length / 2; idx++) {
            temp = array[idx];
            array[idx] = array[last - idx];
            array[last - idx] = temp;
        }
        return array;
    }
}
