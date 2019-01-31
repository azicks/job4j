package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.KingBlack;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = true;
        try {
            int index = this.findBy(source);
            this.checkWay(this.figures[index].way(source, dest));
            this.figures[index] = this.figures[index].copy(dest);
        } catch (FigureNotFoundException | ImpossibleMoveException | OccupiedWayException e) {
            rst = false;
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException("figure not found");
        }
        return rst;
    }

    private void checkWay(Cell[] steps) throws OccupiedWayException {
        if (steps.length == 0) {
            throw new OccupiedWayException("null move");
        }
        for (Cell step : steps) {
            try {
                int idx = this.findBy(step);
                if (!(this.figures[idx] instanceof KingBlack)) {
                    throw new OccupiedWayException("Occupied way");
                }
            } catch (FigureNotFoundException ignored) {
            }
        }
    }
}
