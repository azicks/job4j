package ru.job4j.chess.exceptions;

public class ImpossibleMoveException extends Exception {

    public ImpossibleMoveException() {
        super();
    }

    public ImpossibleMoveException(String msg) {
        super(msg);
    }

}
