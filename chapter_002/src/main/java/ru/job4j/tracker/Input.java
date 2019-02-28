package ru.job4j.tracker;

import java.util.function.Consumer;

public interface Input {
    String ask(String question);
    int ask(String question, int[] range);
    void print(String data);
    void setOutput(Consumer<String> output);
}
