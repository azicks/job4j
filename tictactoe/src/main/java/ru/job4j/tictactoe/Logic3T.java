package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return this.isLineWinner(Figure3T::hasMarkX)
                || this.isDiagonalWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                || this.isLineWinner(Figure3T::hasMarkO)
                || this.isDiagonalWinner(Figure3T::hasMarkO);
    }

    private boolean isLineWinner(Predicate<Figure3T> predicate) {
        boolean result = false;
        for (int index = 0; index != this.table.length; index++) {
            if (this.fillBy(predicate, index, 0, 0, 1)
                    || this.fillBy(predicate, 0, index, 1, 0)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean isDiagonalWinner(Predicate<Figure3T> predicate) {
        return this.fillBy(predicate, 0, 0, 1, 1)
                || this.fillBy(predicate, this.table.length - 1, 0, -1, 1);
    }

    public boolean hasGap() {
        boolean hasGap = false;
        for (int i = 0; i != this.table.length; i++) {
            for (int j = 0; j != this.table.length; j++) {
                Figure3T cell = this.table[i][j];
                hasGap |= !(cell.hasMarkO() || cell.hasMarkX());
                if (hasGap) {
                    break;
                }
            }
        }
        return hasGap;
    }
}
