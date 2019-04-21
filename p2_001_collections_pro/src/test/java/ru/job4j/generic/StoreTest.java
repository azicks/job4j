package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {

    private UserStore us;

    @Before
    public void before() {
        us = new UserStore(3);
        us.add(new User("1"));
        us.add(new User("2"));
        us.add(new User("3"));
    }

    @Test
    public void test() {
        User u = us.findById("2");
        assertThat(u.getId(), is("2"));
        assertTrue(us.delete("1"));
        assertNull(us.findById("1"));
    }
}
