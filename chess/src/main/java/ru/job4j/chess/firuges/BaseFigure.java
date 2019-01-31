package ru.job4j.chess.firuges;

import ru.job4j.chess.CellWrapper;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class BaseFigure implements Figure {

    protected final Cell position;

    public BaseFigure(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    protected void checkDestRange(Cell dest) throws ImpossibleMoveException {
        if (!(
                (dest.x >= 0)
                        && (dest.x <= 7)
                        && (dest.y >= 0)
                        && (dest.y <= 7))) {
            throw new ImpossibleMoveException("out of board");
        }
    }

    protected boolean isFrontalMove(Cell source, Cell dest) {
        boolean rst = false;
        if (source.x == dest.x || source.y == dest.y) {
            rst = true;
        }
        return rst;
    }

    protected boolean isDiagonalMove(Cell source, Cell dest) {
        boolean rst = false;
        if (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)) {
            rst = true;
        }
        return rst;
    }

    protected Cell[] getWayFrontalOrDiagonal(Cell source, Cell dest) {
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        int numSteps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        Cell[] steps = new Cell[numSteps];
        for (int i = 0; i < numSteps; i++) {
            steps[i] = CellWrapper.cellByCoords(source.x + Integer.signum(deltaX) * (i + 1),
                    source.y + Integer.signum(deltaY) * (i + 1));
        }
        return steps;
    }
}
