package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {


    @Test
    public void testMap() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        String str = new StringBuilder()
                .append("1 : One").append(System.lineSeparator())
                .append("2 : Two").append(System.lineSeparator())
                .append("3 : Three").append(System.lineSeparator())
                .toString();
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        assertFalse(map.insert(1, "One"));
        assertThat(map.get(2), is("Two"));
        assertEquals(map.toString(), str);

        assertTrue(map.delete(3));
    }

    @Test
    public void whenAdd15ElementsThenCapacityIs32() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        assertThat(map.getCapacity(), is(16));
        for (int i = 1; i != 15; i++) {
            map.insert(i, "123");
        }
        assertThat(map.getCapacity(), is(32));
    }

}
