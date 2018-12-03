package ru.job4j.array;

/**
 * @since 03.12.2018
 */
public class Check {

    public boolean mono(boolean[] data) {

        boolean result = true;

        if (data.length > 1) {
            for (int idx = 1; idx != data.length; idx++) {
                result &= data[idx] == data[idx - 1];
            }
        }
        return result;
    }
}