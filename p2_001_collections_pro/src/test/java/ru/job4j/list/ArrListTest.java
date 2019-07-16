package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArrListTest {

    private ArrList<Integer> list;

    @Before
    public void beforeTest() {
        list = new ArrList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
    }

    @Test
    public void whenAddMoreElementsthenNoError() {
        list.add(6);
        list.add(7);
    }

    @Test
    public void whenGetSecondThenTwo() {
        assertThat(list.get(1), is(2));
    }

    /*    @Test
    public void ttt() {
        for (int i : list) {
            System.out.println(i);
        }
    }*/
}