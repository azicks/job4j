package ru.job4j.tracker;

/**
 * @since 14.01.2019
 */
public abstract class BaseAction implements UserAction {
    private int id;
    private String name;

    BaseAction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return String.format("%d. %s", this.id, this.name);
    }
}
