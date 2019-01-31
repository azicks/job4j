package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Rook extends BaseFigure {

    public Rook(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        this.checkDestRange(dest);
        if (!isFrontalMove(source, dest)) {
            throw new ImpossibleMoveException("not frontal rook move");
        }
        return this.getWayFrontalOrDiagonal(source, dest);
    }
}
