package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetTwoResultOne() {
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteFirstElementThenTwoOne() {
        assertThat(list.delete(0), is(3));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        assertNull(list.get(2));
    }

    @Test
    public void whenDeleteSecondElementThenThreeOne() {
        assertThat(list.delete(1), is(2));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(1));
        assertNull(list.get(2));
    }

    @Test
    public void whenDeleteFirstElementThenTwoOne2() {
        assertThat(list.deleteFirst(), is(3));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        assertNull(list.get(2));
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
