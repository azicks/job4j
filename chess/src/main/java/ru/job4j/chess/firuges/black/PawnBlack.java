package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.BaseFigure;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class PawnBlack extends BaseFigure {
    public PawnBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[]{dest};
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
