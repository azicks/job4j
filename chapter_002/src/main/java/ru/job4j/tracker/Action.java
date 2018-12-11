package ru.job4j.tracker;

/**
 * Действия
 *
 * @since 11.12.2018
 */
public class Action {
    public static final String[] ACTIONS = {
            "0. Выход",
            "1. Добавить заявку",
            "2. Редактировать заявку",
            "3. Удалить заявку",
            "4. Найти заявку по ID",
            "5. Поиск зявок по имени",
            "6. Показать все заявки"};
    private String name;
    private int id;
    private Tracker tracker;
    private Input input;


    Action(int id, Tracker tracker, Input input) {
        this.id = id;
        this.name = ACTIONS[id];
        this.tracker = tracker;
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public boolean execute() {
        boolean exit = false;
        switch (this.id) {
            case 0:
                this.input.print("До свидания.");
                exit = true;
                break;
            case 1:
                this.createItem();
                break;
            case 2:
                this.editItem();
                break;
            case 3:
                this.deleteItem();
                break;
            case 4:
                this.findById();
                break;
            case 5:
                this.findByName();
                break;
            case 6:
                this.findAll();
                break;
            default:
                break;
        }
        return exit;
    }

    private void findById() {
        String itemId = this.input.ask("Введите ID заявки");
        Item item = this.tracker.findById(itemId);
        if (item == null) {
            this.input.print("Заявка с ID " + itemId + " не найдена.\n");
        } else {
            this.showItem(item);
        }
    }

    private void findByName() {
        String name = this.input.ask("Введите имя заявки");
        Item[] items = this.tracker.findByName(name);
        this.showItems(items);
    }

    private void findAll() {
        Item[] items = this.tracker.findAll();
        this.showItems(items);
    }

    private void createItem() {
        Item item = new Item(
                this.input.ask("Введите имя заявки"),
                this.input.ask("Введите описание заявки"),
                System.currentTimeMillis());
        this.tracker.add(item);
        input.print("Заявка добавлена, ее ID:" + item.getId() + "\n");
    }

    private void editItem() {
        String itemId = this.input.ask("Введите ID заявки для редактирования");
        if (this.checkId(itemId)) {
            Item newItem = new Item(
                    this.input.ask("Введите новое имя заявки"),
                    this.input.ask("Введите новое описание заявки"),
                    System.currentTimeMillis());
            if (tracker.replace(itemId, newItem)) {
                this.input.print("Успешно.\n");
            } else {
                this.input.print("Не удалось\n");
            }
        }
    }

    private void deleteItem() {
        String itemId = this.input.ask("Введите ID заявки для удаления");
        if (this.checkId(itemId)) {
            if (tracker.delete(itemId)) {
                this.input.print("Успешно.\n");
            } else {
                this.input.print("Не удалось\n");
            }
        }
    }

    private void showItem(Item item) {
        this.input.print("Заявка с ID " + item.getId() + ":");
        this.input.print("Имя заявки: " + item.getName());
        this.input.print("Описание заявки: " + item.getDesc() + "\n");
    }

    private void showItems(Item[] items) {
        if (items.length == 0) {
            this.input.print("Заявки не найдены. \n");
        } else {
            for (Item item : items) {
                this.showItem(item);
            }
        }
    }

    private boolean checkId(String id) {
        boolean result = true;
        if (this.tracker.findById(id) == null) {
            this.input.print("Заявка с ID " + id + " не найдена.\n");
            result = false;
        }
        return result;
    }
}
