package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
     * @param id      ID заявки
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
        return this.items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
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
        return this.items.stream()
                .filter(item -> item.getName().equals(key))
                .collect(Collectors.toList());
    }

    /**
     * Ищет индекс заявки в массиве по ID
     *
     * @param id ID
     * @return index
     */
    private int findIndexById(String id) {
        int index = -1;
        for (int i = 0; i != this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                index = i;
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
