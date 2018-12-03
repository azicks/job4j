package ru.job4j.array;

/**
 * Обертка над строкой.
 *
 * @since 03.12.2018
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {

        boolean result = true;
        char[] value = prefix.toCharArray();

        if (value.length <= data.length) {
            for (int idx = 0; idx != prefix.length(); idx++) {
                result &= value[idx] == data[idx];
            }
        } else {
            result = false;
        }

        return result;
    }
}
