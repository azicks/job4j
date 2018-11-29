package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author DK
 * @since 29.11.2018
 */
public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenThirdIsMax() {
        Max maxim = new Max();
        int result = maxim.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenSecondIsMax() {
        Max maxim = new Max();
        int result = maxim.max(1, 20, 3);
        assertThat(result, is(20));
    }

    @Test
    public void whenFirstIsMax() {
        Max maxim = new Max();
        int result = maxim.max(10, 2, 3);
        assertThat(result, is(10));
    }
}