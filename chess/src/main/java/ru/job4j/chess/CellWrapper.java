package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;

public class CellWrapper {

    public static Cell cellByCoords(int x, int y) {
        Cell cell = null;
        for (Cell c : Cell.values()) {
            if (c.x == x && c.y == y) {
                cell = c;
            }
        }
        return cell;
    }
}
