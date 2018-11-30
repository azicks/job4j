package ru.job4j.loop;

/**
 * Шахматная доска
 *
 * @since 30.11.2018
 */
public class Board {

    /**
     * @param width  ширина доски
     * @param height высота доски
     * @return Вид доски в псевдографике
     */
    public String paint(int width, int height) {

        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {

                // условие проверки, что писать пробел или X
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        System.out.println(screen.toString());
        return screen.toString();

    }
}
