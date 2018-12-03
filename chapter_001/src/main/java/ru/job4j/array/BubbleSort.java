package ru.job4j.array;

/**
 * Пузырьковая сортировка
 *
 * @since 03.12.2018
 */
public class BubbleSort {

    private int[] data;

    public BubbleSort(int[] data) {
        this.data = data;
    }

    /**
     * Сортировщик
     *
     * @return Результат сортировки
     */
    public int[] sort() {

        for (int back = data.length - 1; back > 0; back--) {
            for (int idx = 0; idx < back; idx++) {
                if (data[idx] > data[idx + 1]) {
                    int temp = data[idx];
                    data[idx] = data[idx + 1];
                    data[idx + 1] = temp;
                }
            }
        }
        return data;
    }
}
