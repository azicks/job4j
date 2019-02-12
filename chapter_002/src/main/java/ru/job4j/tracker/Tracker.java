package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @since 10.12.2018
 */
public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private static final Random RND = new Random();

    /**
     * Добавляет заявку в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Заменяет заявку в хранилище по ID заявки
     *
     * @param id   ID заявки
     * @param newItem Новая заявка
     */
    public boolean replace(String id, Item newItem) {
        boolean success = false;
        int idx = this.findIndexById(id);
        if (idx != -1) {
            this.items.set(idx, newItem);
            newItem.setId(id);
            success = true;
        }
        return success;
    }

    /**
     * Удаляет заявку по ID
     *
     * @param id ID
     */
    public boolean delete(String id) {
        boolean success = false;
        int idx = this.findIndexById(id);
        if (idx != -1) {
            this.items.remove(idx);
            success = true;
        }
        return success;
    }

    /**
     * Ищет заявку по ID, если не найдено возвращает null
     *
     * @param id ID заявки
     * @return Заявка
     */
    public Item findById(String id) {
        Item result = null;
        int idx = this.findIndexById(id);
        if (idx != -1) {
            result = this.items.get(idx);
        }
        return result;
    }

    /**
     * Возвращает копию массива this.items без null элементов
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Возвращет массив заявок с одинаковым названием.
     *
     * @param key Имя заявки для поиска
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Ищет индекс заявки в массиве по ID
     *
     * @param id ID
     * @return index
     */
    private int findIndexById(String id) {
        int index = -1;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                index = this.items.indexOf(item);
                break;
            }
        }
        return index;
    }

    /**
     * Генерирует уникальный ключ для заявки
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        return Long.toString((System.currentTimeMillis() + RND.nextInt(100)));
    }
}
