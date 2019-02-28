package ru.job4j.tracker;

import java.util.function.Consumer;

public class ValidateInput implements Input {

    private final Input input;
    private Consumer<String> output;

    @Override
    public void setOutput(Consumer<String> output) {
        this.output = output;
        this.input.setOutput(output);
    }

    ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public void print(String data) {
        this.input.print(data);
    }
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        int result = -1;
        boolean invalid = true;
        do {
            try {
                try {
                    result = this.input.ask(question, range);
                } catch (NumberFormatException e) {
                    throw new MenuWrongIndexException("Введено некорректное значение.", e);
                }
                invalid = false;
            } catch (MenuWrongIndexException e) {
                this.input.print(e.getMessage());
            }
        }
        while (invalid);
        return result;
    }
}
