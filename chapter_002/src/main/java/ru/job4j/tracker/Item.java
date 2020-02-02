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

    Item(String id, String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.id = id;
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        Item i = (Item) obj;
        if (!(this.id.equals(i.id) && this.name.equals(i.name) && this.desc.equals(i.desc)/* && this.created == i.created*/)) {
            return false;
        }
        return true;
    }
}
