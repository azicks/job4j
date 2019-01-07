package ru.job4j.tracker;

import java.util.Scanner;

/**
 * @since 11.12.2018
 */
public class ConsoleInput implements Input {

    @Override
    public String ask(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + ": ");
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        boolean exists = false;
        int key = Integer.valueOf(this.ask(question));
        for (int val : range) {
            if (val == key) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new MenuWrongIndexException("Выбран неверный пункт меню.");
        }
        return key;
    }

    @Override
    public void print(String data) {
        System.out.println(data);
    }
}
