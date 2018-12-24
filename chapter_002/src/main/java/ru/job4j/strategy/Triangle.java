package ru.job4j.strategy;

public class Triangle implements Shape {

    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("  ^  \n");
        result.append(" / \\ \n");
        result.append("/___\\\n");
        return result.toString();
    }
}
