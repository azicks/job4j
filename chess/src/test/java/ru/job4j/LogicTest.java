package ru.job4j;

import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogicTest {

    @Test
    public void whenBishopCollides() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.B2));
        logic.add(new BishopBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.C3));
    }

    @Test
    public void whenBishopGoesNull() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.A1));
    }

    @Test
    public void whenBishopNotDiagonal() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.A5));
    }

    @Test
    public void whenBishopGoesNorm() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        assertTrue(logic.move(Cell.A1, Cell.C3));
    }

    @Test
    public void whenRookNotFrontal() {
        Logic logic = new Logic();
        logic.add(new RookBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.B2));
    }

    @Test
    public void whenRookGoesNorm() {
        Logic logic = new Logic();
        logic.add(new RookBlack(Cell.A1));
        assertTrue(logic.move(Cell.A1, Cell.A5));
    }

    @Test
    public void whenPawnGoesWrong() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.A3));
    }

    @Test
    public void whenPawnGoesNorm() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.A2));
        assertTrue(logic.move(Cell.A2, Cell.A1));
    }

    @Test
    public void whenQueenGoesWrong() {
        Logic logic = new Logic();
        logic.add(new QeenBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.B5));
    }

    @Test
    public void whenQueenGoesNorm() {
        Logic logic = new Logic();
        logic.add(new QeenBlack(Cell.A1));
        assertTrue(logic.move(Cell.A1, Cell.C3));
    }

    @Test
    public void whenKingGoesWrong() {
        Logic logic = new Logic();
        logic.add(new KingBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.A3));
    }

    @Test
    public void whenKingGoesNorm() {
        Logic logic = new Logic();
        logic.add(new KingBlack(Cell.A1));
        assertTrue(logic.move(Cell.A1, Cell.B2));
    }

    @Test
    public void whenKnightGoesWrong() {
        Logic logic = new Logic();
        logic.add(new KnightBlack(Cell.A1));
        assertFalse(logic.move(Cell.A1, Cell.A5));
    }

    @Test
    public void whenKnightGoesNorm() {
        Logic logic = new Logic();
        logic.add(new KnightBlack(Cell.A1));
        assertTrue(logic.move(Cell.A1, Cell.B3));
    }
}
