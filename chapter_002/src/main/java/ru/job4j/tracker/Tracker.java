package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * @since 10.12.2018
 */
public class Tracker {

    private final Item[] items = new Item[100];
    private int position = 0;
    private static final Random RND = new Random();

    /**
     * Добавляет заявку в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items[this.position++] = item;
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
            this.items[idx] = newItem;
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
            System.arraycopy(this.items, idx + 1, this.items, idx, position--);
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
        for (int i = 0; i != position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает копию массива this.items без null элементов
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }

    /**
     * Возвращет массив заявок с одинаковым названием.
     *
     * @param key Имя заявки для поиска
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int numOfItems = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[numOfItems++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, numOfItems);
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
            for (int i = 0; i != position; i++) {
                if (this.items[i].equals(item)) {
                    index = i;
                }
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
