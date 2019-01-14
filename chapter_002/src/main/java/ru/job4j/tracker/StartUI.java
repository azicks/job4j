package ru.job4j.tracker;

/**
 * @since 11.12.2018
 */
public class StartUI {
    private Input input;
    private MenuTracker menu;
    private Tracker tracker;

    StartUI(Input input) {
        this.input = input;
        this.tracker = new Tracker();
        init();
    }

    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        init();
    }

    private void init() {
        this.menu = new MenuTracker(tracker, input);
        do {
            menu.show();
            menu.select();
        } while (menu.execute());
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()));
    }
}
