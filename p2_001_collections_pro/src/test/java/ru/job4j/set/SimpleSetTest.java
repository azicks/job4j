package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private SimpleSet<Integer> set = new SimpleSet<>();

    @Test
    public void setTest() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        assertThat(set.getSize(), is(3));
        /*for (int i : set) {
            System.out.println(i);
        }*/
    }


}