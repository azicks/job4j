package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Bishop extends BaseFigure {

    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        this.checkDestRange(dest);
        if (!this.isDiagonalMove(source, dest)) {
            throw new ImpossibleMoveException("not diagonal bishop move");
        }
        return this.getWayFrontalOrDiagonal(source, dest);
    }
}
