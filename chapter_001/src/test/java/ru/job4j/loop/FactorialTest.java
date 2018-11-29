package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        //факториал для числа 5 равен 120.
        Factorial f = new Factorial();
        assertThat(f.calc(5), is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        //факториал для числа 0 равен 1.
        Factorial f = new Factorial();
        assertThat(f.calc(0), is(1));
    }
}