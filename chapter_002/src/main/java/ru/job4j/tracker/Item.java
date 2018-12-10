package ru.job4j.tracker;

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
        return this.name;
    }

    public void setName(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
}
