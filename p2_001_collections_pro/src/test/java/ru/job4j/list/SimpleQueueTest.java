package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    private SimpleQueue<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleQueue<>();
        list.push(1);
        list.push(2);
        list.push(3);
    }

    @Test
    public void test() {
        assertThat(list.getSize(), is(3));
        assertThat(list.poll(), is(1));
        assertThat(list.getSize(), is(2));
        assertThat(list.poll(), is(2));
        assertThat(list.getSize(), is(1));
        assertThat(list.poll(), is(3));
        assertThat(list.getSize(), is(0));
        assertNull(list.poll());
        assertThat(list.getSize(), is(0));
    }

}
