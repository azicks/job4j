package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.Rook;

public class RookWhite extends Rook {

    public RookWhite(Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
