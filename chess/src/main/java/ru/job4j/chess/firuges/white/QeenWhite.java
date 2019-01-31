package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.Queen;

public class QeenWhite extends Queen {

    public QeenWhite(final Cell position) {
        super(position);
    }

   @Override
    public Figure copy(Cell dest) {
        return new QeenWhite(dest);
    }
}
