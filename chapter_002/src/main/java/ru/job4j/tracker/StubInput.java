package ru.job4j.tracker;

import java.util.function.Consumer;

public class StubInput implements Input {

    private final String[] value;
    private int position;
    private Consumer<String> output;

    @Override
    public void setOutput(Consumer<String> output) {
        this.output = output;
    }

    public StubInput(final String[] value) {
        this.value = value;
        this.output = System.out::print;
    }

    @Override
    public String ask(String question) {
        output.accept(question + ": ");
        output.accept(this.value[this.position] + System.lineSeparator());
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, int[] range) {
        boolean exists = false;
        int result = Integer.valueOf(this.ask(question));
        for (int val : range) {
            if (val == result) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new MenuWrongIndexException("Выбран неверный пункт меню.");
        }
        return result;
    }

    @Override
    public void print(String data) {
        output.accept(data + System.lineSeparator());
    }
}
