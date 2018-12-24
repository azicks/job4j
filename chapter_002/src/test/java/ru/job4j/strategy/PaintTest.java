package ru.job4j.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {

    public final PrintStream stdout = System.out;
    public final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOut() {
        System.setOut(stdout);
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        String expected = new StringBuilder()
                .append("x---x\n")
                .append("|   |\n")
                .append("|   |\n")
                .append("x---x\n")
                .append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        String expected = new StringBuilder()
                .append("  ^  \n")
                .append(" / \\ \n")
                .append("/___\\\n")
                .append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }
}
