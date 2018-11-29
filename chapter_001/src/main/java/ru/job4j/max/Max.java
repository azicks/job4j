package ru.job4j.max;

/**
 * @author DK
 * @since 29.11.2018
 */
public class Max {

    /**
     * Max method with two params.
     *
     * @param first  first int
     * @param second second ind
     * @return maximum of first and second.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Max method with three params.
     *
     * @return maximum.
     */
    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), third);
    }
}
