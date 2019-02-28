package ru.job4j.tracker;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @since 11.12.2018
 */
public class ConsoleInput implements Input {

    private Consumer<String> output;

    @Override
    public void setOutput(Consumer<String> output) {
        this.output = output;
    }

    @Override
    public String ask(String question) {
        Scanner scanner = new Scanner(System.in);
        output.accept(question + ": ");
        return scanner.nextLine();
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
