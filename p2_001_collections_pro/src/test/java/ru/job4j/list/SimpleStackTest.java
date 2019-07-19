package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    private SimpleStack<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
    }

    @Test
    public void whenAddThreeElementsThenPollFirstElementResultThreeThenPollSecondElementResultTwoAndThenCheckSizeOfCollectionResultIsOne() {
        assertThat(list.poll(), is(3));
        assertThat(list.peek(), is(2));
        assertThat(list.poll(), is(2));
        assertThat(list.getSize(), is(1));
    }

    @Test
    public void whenIterateThen321() {
        Iterator it = list.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        assertFalse(it.hasNext());
    }
}
