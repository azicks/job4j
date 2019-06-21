package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
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
}
