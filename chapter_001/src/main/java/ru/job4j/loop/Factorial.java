package ru.job4j.loop;

/**
 * Факториал
 * @author DK
 * @since 29.11.2018
 */
public class Factorial {

    /**
     * Вычисляет факториал
     * @param n число
     * @return факториал числа
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
