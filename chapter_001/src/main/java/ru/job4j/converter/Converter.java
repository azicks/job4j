package ru.job4j.converter;

/**
 * Currency converter
 */
public class Converter {

    static final int EURRUB = 70;
    static final int USDRUB = 60;

    /**
     * Конвертируем рубли в евро.
     *
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / EURRUB;
    }

    /**
     * Конвертируем рубли в доллары.
     *
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return value / USDRUB;
    }

    /**
     * Конвертируем евро в рубли.
     *
     * @param value евро.
     * @return Рубли
     */
    public int euroToRuble(int value) {
        return value * EURRUB;
    }

    /**
     * Конвертируем доллары в рубли.
     *
     * @param value доллары.
     * @return Рубли
     */
    public int dollarToRuble(int value) {
        return value * USDRUB;
    }
}
