package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShapeTest {

    @Test
    public void whenDrawSquare() {
        Shape shape = new Square();
        String expected = new StringBuilder()
                .append("x---x\n")
                .append("|   |\n")
                .append("|   |\n")
                .append("x---x\n").toString();
        assertThat(shape.draw(), is(expected));
    }

    @Test
    public void whenDrawTriangle() {
        Shape shape = new Triangle();
        String expected = new StringBuilder()
                .append("  ^  \n")
                .append(" / \\ \n")
                .append("/___\\\n").toString();
        assertThat(shape.draw(), is(expected));
    }
}
