package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String question, int[] range) {
        int result = -1;
        boolean invalid = true;
        do {
            try {
                try {
                    result = super.ask(question, range);
                } catch (NumberFormatException e) {
                    throw new MenuWrongIndexException("Введено некорректное значение.", e);
                }
                invalid = false;
            } catch (MenuWrongIndexException e) {
                super.print(e.getMessage());
            }
        }
        while (invalid);
        return result;
    }
}
