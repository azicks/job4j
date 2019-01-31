package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.King;

public class KingWhite extends King {
    public KingWhite(final Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}
