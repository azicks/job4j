package ru.job4j.tracker;

public class ValidateInput implements Input {

    private final Input input;

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
        boolean exists = false;
        int result = -1;
        boolean invalid = true;
        do {
            try {
                try {
                    result = this.input.ask(question, range);
                    for (int val : range) {
                        if (val == result) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        throw new MenuWrongIndexException("Выбран неверный пункт меню.");
                    }
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
