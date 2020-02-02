package ru.job4j.tracker;

public class StartUISQL {

    public static void main(String[] args) {
        try (TrackerSQL tr = new TrackerSQL()) {
            if (tr.init()) {
                new StartUI(new ValidateInput(new ConsoleInput()), System.out::print, tr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}