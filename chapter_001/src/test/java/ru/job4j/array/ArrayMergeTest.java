package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayMergeTest {

    @Test
    public void whenMerge123With234Then122334() {
        ArrayMerge am = new ArrayMerge();
        int[] input1 = {1, 2, 3};
        int[] input2 = {2, 3, 4};
        int[] expected = {1, 2, 2, 3, 3, 4};

        assertThat(am.merge(input1, input2), is(expected));
    }

    @Test
    public void whenMerge123With4567Then124567() {
        ArrayMerge am = new ArrayMerge();
        int[] input1 = {1, 2, 3};
        int[] input2 = {4, 5, 6, 7};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};

        assertThat(am.merge(input1, input2), is(expected));
    }
}
