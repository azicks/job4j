package ru.job4j.tracker;

/**
 * Обработчик меню.
 *
 * @since 11.12.2018
 */
public class MenuTracker {
    private Action[] actions;
    private Input input;
    private Tracker tracker;
    private int selected = -1;

    public MenuTracker(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        this.init();
    }

    private void init() {
        this.actions = new Action[Action.ACTIONS.length];
        for (int i = 0; i != this.actions.length; i++) {
            actions[i] = new Action(i, this.tracker, this.input);
        }
    }

    public void show() {
        for (Action action : this.actions) {
            this.input.print(action.getName());
        }
        this.input.print("");
    }

    /**
     * Делает выбор в меню
     */
    public void select() {
        do {
            try {
                this.selected = Integer.parseInt(this.input.ask("Выберите действие"));
            } catch (Exception e) { }
        } while (!(this.selected < this.actions.length && this.selected >= 0));
        this.input.print("");
    }

    /**
     * Выполняет выбранное действие
     *
     * @return true - команда выхода
     */
    public boolean execute() {
        return this.actions[this.selected].execute();
    }

}
