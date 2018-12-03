package ru.job4j.array;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {

    @Test
    public void when1223455Then12345() {

        String[] input = {"One", "Two", "Two", "Three", "Four", "Five", "Five"};
        String[] expected = {"One", "Two", "Three", "Four", "Five"};
        ArrayDuplicate dup = new ArrayDuplicate();
        String[] result = dup.remove(input);
        System.out.println(Arrays.toString(result));
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    @Test
    public void when12234555Then12345() {

        String[] input = {"One", "Two", "Two", "Three", "Four", "Five", "Five", "Five"};
        String[] expected = {"One", "Two", "Three", "Four", "Five"};
        ArrayDuplicate dup = new ArrayDuplicate();
        String[] result = dup.remove(input);
        System.out.println(Arrays.toString(result));
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    @Test
    public void when12345Then12345() {

        String[] input = {"One", "Two", "Three", "Four", "Five"};
        String[] expected = {"One", "Two", "Three", "Four", "Five"};
        ArrayDuplicate dup = new ArrayDuplicate();
        String[] result = dup.remove(input);
        System.out.println(Arrays.toString(result));
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

}