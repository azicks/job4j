package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Bishop;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopWhite extends Bishop {
     public BishopWhite(final Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
