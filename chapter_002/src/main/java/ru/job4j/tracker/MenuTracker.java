package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик меню.
 *
 * @since 11.12.2018
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private int selected = -1;
    private List<UserAction> actions = new ArrayList<>();

    public int[] getActionsIds() {
        int[] result = new int[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            result[i] = actions.get(i).getId();
        }
        return result;
    }

    public MenuTracker(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        this.fillActions();
    }

    public void show() {
        for (UserAction action : this.actions) {
            this.input.print(action.getName());
        }
        this.input.print("");
    }

    /**
     * Делает выбор в меню
     */
    public void select() {
        this.selected = this.input.ask("Выберите действие", this.getActionsIds());
        this.input.print("");
    }

    /**
     * Выполняет выбранное действие
     *
     * @return false - команда выхода
     */
    public boolean execute() {
        return this.actions.get(this.selected).execute();
    }

    private void fillActions() {
        this.actions.add(new ExitProgram(0, "Выход"));
        this.actions.add(new CreateItem(1, "Добавить заявку"));
        this.actions.add(new EditItem(2, "Редактировать заявку"));
        this.actions.add(new DeleteItem(3, "Удалить заявку"));
        this.actions.add(new FindItemById(4, "Найти заявку по ID"));
        this.actions.add(new FindItemByName(5, "Поиск зявок по имени"));
        this.actions.add(new ShowAll(6, "Показать все заявки"));
    }

    private class CreateItem extends BaseAction {
        CreateItem(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            Item item = new Item(
                    input.ask("Введите имя заявки"),
                    input.ask("Введите описание заявки"),
                    System.currentTimeMillis());
            tracker.add(item);
            input.print("Заявка добавлена, ее ID:" + item.getId() + System.lineSeparator());
            return true;
        }
    }

    private class EditItem extends BaseAction {
        EditItem(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            String itemId = input.ask("Введите ID заявки для редактирования");
            Item newItem = new Item(
                    input.ask("Введите новое имя заявки"),
                    input.ask("Введите новое описание заявки"),
                    System.currentTimeMillis());
            if (tracker.replace(itemId, newItem)) {
                input.print("Успешно." + System.lineSeparator());
            } else {
                input.print("Не удалось" + System.lineSeparator());
            }
            return true;
        }
    }

    private class DeleteItem extends BaseAction {
        DeleteItem(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            String itemId = input.ask("Введите ID заявки для удаления");
            if (tracker.delete(itemId)) {
                input.print("Успешно." + System.lineSeparator());
            } else {
                input.print("Не удалось" + System.lineSeparator());
            }
            return true;
        }
    }

    private class FindItemById extends BaseAction {
        FindItemById(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            String itemId = input.ask("Введите ID заявки");
            Item item = tracker.findById(itemId);
            if (item == null) {
                input.print("Заявка с ID " + itemId + " не найдена." + System.lineSeparator());
            } else {
                showItem(item);
            }
            return true;
        }
    }

    private class FindItemByName extends BaseAction {
        FindItemByName(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            String name = input.ask("Введите имя заявки");
            Item[] items = tracker.findByName(name);
            showItems(items);
            return true;
        }
    }

    private class ShowAll extends BaseAction {
        ShowAll(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            Item[] items = tracker.findAll();
            showItems(items);
            return true;
        }
    }

    private class ExitProgram extends BaseAction {
        ExitProgram(int id, String name) {
            super(id, name);
        }

        @Override
        public boolean execute() {
            input.print("До свидания.");
            return false;
        }
    }

    private void showItem(Item item) {
        this.input.print("Заявка с ID " + item.getId() + ":");
        this.input.print("Имя заявки: " + item.getName());
        this.input.print("Описание заявки: " + item.getDesc());
        this.input.print("");
    }

    private void showItems(Item[] items) {
        if (items.length == 0) {
            this.input.print("Заявки не найдены.");
            this.input.print("");
        } else {
            for (Item item : items) {
                this.showItem(item);
            }
        }
    }
}
