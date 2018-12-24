package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {

    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        String expected = new StringBuilder()
                .append("x---x\n")
                .append("|   |\n")
                .append("|   |\n")
                .append("x---x\n")
                .append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expected));
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        String expected = new StringBuilder()
                .append("  ^  \n")
                .append(" / \\ \n")
                .append("/___\\\n")
                .append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expected));
        System.setOut(stdout);
    }
}
