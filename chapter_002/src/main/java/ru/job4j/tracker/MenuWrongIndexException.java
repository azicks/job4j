package ru.job4j.tracker;

public class MenuWrongIndexException extends RuntimeException {

    MenuWrongIndexException(String msg) {
        super(msg);
    }
    MenuWrongIndexException(String msg, Exception e) {
        super(msg, e);
    }
}
