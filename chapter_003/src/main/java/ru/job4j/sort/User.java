package ru.job4j.sort;

public class User implements Comparable<User>{
    private int age;
    private String name;

    public int getAge() {
        return this.age;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(User u) {
        return Integer.compare(this.age, u.getAge());
    }
}
