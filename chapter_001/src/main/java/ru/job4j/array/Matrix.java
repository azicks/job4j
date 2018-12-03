package ru.job4j.array;

/**
 * @since 03.12.2018
 */
public class Matrix {

    /**
     *
     * @param size Размерность матрицы
     * @return 2D массив произведений
     */
    int[][] multiple(int size) {

        int[][] matrix = new int[size][size];

        for (int column = 0; column != size; column++) {
            for (int row = 0; row != size; row++) {
                matrix[column][row] = (column + 1) * (row + 1);
            }
        }
        return matrix;
    }
}
