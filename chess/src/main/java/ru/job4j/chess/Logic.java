package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.KingBlack;

import java.util.stream.IntStream;

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
        IntStream.range(0, this.figures.length).forEach(i -> this.figures[i] = null);
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        return IntStream.range(0, this.figures.length)
                .filter(i -> this.figures[i] != null && this.figures[i].position().equals(cell))
                .findFirst().orElseThrow(() -> new FigureNotFoundException("figure not found"));
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
