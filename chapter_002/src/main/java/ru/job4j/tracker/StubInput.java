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
        System.out.println(data);
    }
}
