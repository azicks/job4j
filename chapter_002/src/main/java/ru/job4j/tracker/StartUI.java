package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * @since 11.12.2018
 */
public class StartUI {
    private final Input input;
    private MenuTracker menu;
    private final ITracker tracker;

    StartUI(Input input, Consumer<String> output) {
        this.input = input;
        this.tracker = new Tracker();
        this.input.setOutput(output);
        init();
    }

    StartUI(Input input, Consumer<String> output, ITracker tracker) {
        this.input = input;
        this.input.setOutput(output);
        this.tracker = tracker;
        init();
    }

    StartUI(Input input, ITracker tracker) {
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
        new StartUI(new ValidateInput(new ConsoleInput()), System.out::print);
    }
}
