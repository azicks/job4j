package ru.job4j.tracker;

public class StubInput implements Input {

    private final String[] value;
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        System.out.print(question + ": ");
        System.out.println(this.value[this.position]);
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, int[] range) {
        return Integer.valueOf(this.ask(question));
    }

    @Override
    public void print(String data) {
        System.out.println(data);
    }
}
