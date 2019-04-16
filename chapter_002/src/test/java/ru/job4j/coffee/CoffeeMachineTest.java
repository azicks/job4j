package ru.job4j.coffee;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    @Test
    public void whenValue100Cost75thenChange10105() {
        assertThat(new CoffeeMachine().change(100, 75), is(new int[]{10, 10, 5}));
    }

    @Test
    public void whenValue50Cost50thenChange10105() {
        assertThat(new CoffeeMachine().change(50, 50), is(new int[]{}));
    }

    @Test
    public void whenValue100Cost88thenChange102() {
        assertThat(new CoffeeMachine().change(100, 88), is(new int[]{10, 2}));
    }

    @Test
    public void whenValue100Cost74thenChange101051() {
        assertThat(new CoffeeMachine().change(100, 74), is(new int[]{10, 10, 5, 1}));
    }

    @Test
    public void whenValue50Cost75thenChangeNull() {
        assertNull(new CoffeeMachine().change(50, 75));
    }

    @Test
    public void whenValue85ChangeNull() {
        assertNull(new CoffeeMachine().change(85, 75));
    }
}
