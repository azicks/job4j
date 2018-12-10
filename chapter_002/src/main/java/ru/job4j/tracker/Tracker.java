package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * @since 10.12.2018
 */
public class Tracker {

    private static final Random RND = new Random();
    private final Item[] items = new Item[100];
    private int position = 0;

    /**
     * Добавляет заявку в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Заменяет заявку в хранилище по ID заявки
     *
     * @param id   ID заявки
     * @param item Новая заявка
     */
    public void replace(String id, Item item) {
        int idx = this.findIndexById(id);
        if (idx != -1) {
            this.items[idx] = item;
        }
    }

    /**
     * Удаляет заявку по ID
     *
     * @param id ID
     */
    public void delete(String id) {
        int idx = this.findIndexById(id);
        if (idx != -1) {
            Item[] items = this.items.clone();
            System.arraycopy(items, 0, this.items, 0, idx);
            if (idx < this.items.length - 1) {
                System.arraycopy(items, idx + 1, this.items, idx, items.length - 1 - idx);
            }
        }
    }

    /**
     * Ищет заявку по ID
     *
     * @param id ID заявки
     * @return Заявка
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }

    /**
     * Возвращает копию массива this.items без null элементов
     */
    public Item[] findAll() {
        Item[] result = new Item[this.items.length];
        int numOfItems = 0;
        for (Item item : this.items) {
            if (item != null) {
                result[numOfItems++] = item;
            }
        }
        result = Arrays.copyOf(result, numOfItems);
        return result;
    }

    /**
     * Возвращет массив заявок с одинаковым названием.
     *
     * @param key Имя заявки для поиска
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.items.length];
        int numOfItems = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                result[numOfItems++] = item;
            }
        }
        result = Arrays.copyOf(result, numOfItems);
        return result;
    }

    /**
     * Генерирует уникальный ключ для заявки
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        return Long.toString((System.currentTimeMillis() + RND.nextInt(100)));
    }

    /**
     * Ищет индекс заявки в массиве по ID
     *
     * @param id ID
     * @return index
     */
    private int findIndexById(String id) {
        int index = -1;
        Item item = this.findById(id);
        if (item != null) {
            for (int i = 0; i != this.items.length; i++) {
                if (this.items[i] != null && this.items[i].equals(item)) {
                    index = i;
                }
            }
        }
        return index;
    }
}
