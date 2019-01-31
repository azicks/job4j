package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Queen extends BaseFigure {

    public Queen(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        this.checkDestRange(dest);
        if (!(isFrontalMove(source, dest) || isDiagonalMove(source, dest))) {
            throw new ImpossibleMoveException("not a queen move");
        }
        return this.getWayFrontalOrDiagonal(source, dest);
    }
}
