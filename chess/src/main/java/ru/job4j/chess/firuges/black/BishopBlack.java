package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Bishop;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack extends Bishop {

    public BishopBlack(final Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
