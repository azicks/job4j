package ru.job4j.strategy;

public class Square implements Shape {

    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("x---x\n");
        result.append("|   |\n");
        result.append("|   |\n");
        result.append("x---x\n");
        return result.toString();
    }
}
