package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void loadSys() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "0"})
        );
        String expected = new StringBuilder()
                .append("Enter: invalid").append(System.lineSeparator())
                .append("Введено некорректное значение.").append(System.lineSeparator())
                .append("Enter: 0").append(System.lineSeparator())
                .toString();
        input.ask("Enter", new int[]{1});
        assertThat(this.out.toString(), is(expected));
    }
}
