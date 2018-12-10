package ru.job4j.tracker;

import java.util.Random;

/**
 * Класс заявки
 *
 * @since 10.12.2018
 */
public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;
    private static final Random RND = new Random();

    Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.id = generateId();
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
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
