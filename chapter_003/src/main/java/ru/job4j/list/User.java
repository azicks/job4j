package ru.job4j.list;

public class User {
    private int id;
    private String name;
    private String city;

    User(int id, String name, String city) {
        this.name = name;
        this.city = city;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}
