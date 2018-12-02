package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Пирамидка
 *
 * @since 30.11.2018
 */
public class Paint {

    /**
     * Рисует пирамиду.
     *
     * @param height Высота пирамиды
     * @return Пирамида в псевдографике
     */
    public String pyramid2(int height) {

        StringBuilder screen = new StringBuilder();

        for (int row = 1; row <= height; row++) {
            StringBuilder empty = new StringBuilder();
            StringBuilder brick = new StringBuilder();

            //Расчет кол-ва занятых и пустых (до первой занятой) ячеек для строки
            int bricks = 1 + 2 * (row - 1);
            int empties = (height - row);

            for (int i = 1; i <= bricks; i++) {
                brick.append("^");
            }
            for (int i = 1; i <= empties; i++) {
                empty.append(" ");
            }
            //Составление строки
            screen.append(empty).append(brick).append(empty);
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {

        StringBuilder screen = new StringBuilder();

        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String rightTrl(int height) {

        return this.loopBy(
                height,
                height,
                (row, column) -> (row >= column));
    }

    public String leftTrl(int height) {

        return this.loopBy(
                height,
                height,
                (row, column) -> (row >= height - column - 1));
    }

    public String pyramid(int height) {

        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> (row >= height - column - 1 && row + height - 1 >= column));
    }
}