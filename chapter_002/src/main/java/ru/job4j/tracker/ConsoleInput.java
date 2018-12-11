package ru.job4j.tracker;

import java.util.Scanner;

/**
 * @since 11.12.2018
 */
public class ConsoleInput implements Input {

    public String ask(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + ": ");
        return scanner.nextLine();
    }

    public void print(String data) {
        System.out.println(data);
    }
}
