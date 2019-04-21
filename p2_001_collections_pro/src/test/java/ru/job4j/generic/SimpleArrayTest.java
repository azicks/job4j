package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class SimpleArrayTest {
    private SimpleArray<Integer> sa;

    @Before
    public void before() {
        sa = new SimpleArray<>(5);
        for (int i = 1; i != 6; i++) {
            sa.add(i);
        }
    }

    @Test
    public void whenIterate() {
        int idx = 0;
        for (int i : sa) {
            assertEquals(++idx, i);
        }
        assertEquals(5, idx);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddToFullThenArrayIndexOutOfBoundsException() {
        sa.add(1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetToOutOfBoundsFullThenArrayIndexOutOfBoundsException() {
        sa.set(10, 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getTest() {
        assertThat(sa.get(0), is(1));
        sa.get(10);
    }
}
