package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class King extends BaseFigure {
    public King(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        this.checkDestRange(dest);
        int deltaX = Math.abs(dest.x - source.x);
        int deltaY = Math.abs(dest.y - source.y);
        if (deltaX > 1 || deltaY > 1) {
            throw new ImpossibleMoveException("not a king move");
        }
        return new Cell[]{dest};
    }
}
