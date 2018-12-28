package ru.job4j.tracker;

public abstract class UserAction {
    private int id;
    private String name;

    abstract  boolean execute();

    UserAction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return String.format("%d. %s", id, name);
    }
}
