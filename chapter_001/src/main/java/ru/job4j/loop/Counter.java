package ru.job4j.loop;

/**
 * @author DK
 * @since 29.11.2018
 */
public class Counter {

    /**
     * Метод вычисляет сумму четныx чисел в диапазоне от start до finish.
     *
     * @param start start
     * @param finish finish
     * @return Сумма четныx чисел в диапазоне от start до finish
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (; start <= finish; start++) {
            if (start % 2 == 0) {
                sum += start;
            }
        }
        return sum;
    }
}
