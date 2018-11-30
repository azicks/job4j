package ru.job4j.loop;

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
    public String pyramid(int height) {

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
}