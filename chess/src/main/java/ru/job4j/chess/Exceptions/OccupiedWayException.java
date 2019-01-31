package ru.job4j.chess.exceptions;

public class OccupiedWayException extends Exception {

    public OccupiedWayException() {
        super();
    }

    public OccupiedWayException(String msg) {
        super(msg);
    }

}
