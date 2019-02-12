package ru.job4j.tracker;

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

    Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
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
    public String getDesc() {
        return this.desc;
    }

    @Override
    public boolean equals(Object o) {
        Item i = (Item) o;
        return i.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
