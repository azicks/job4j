package ru.job4j.array;

/**
 * Square class
 *
 * @since 03.12.2018
 */

public class Square {

    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int idx = 0; idx != bound; idx++) {
            rst[idx] = (int) Math.pow(idx + 1, 2);
        }
        return rst;
    }
}
