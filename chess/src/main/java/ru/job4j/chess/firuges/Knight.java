package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Knight extends BaseFigure {

    public Knight(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        this.checkDestRange(dest);
        int deltaX = Math.abs(dest.x - source.x);
        int deltaY = Math.abs(dest.y - source.y);
        if (!(
                ((deltaX == 2) && (deltaY == 1)) || ((deltaX == 1) && (deltaY == 2))
        )) {
            throw new ImpossibleMoveException("not a knight move");
        }
        return new Cell[]{dest};
    }
}
