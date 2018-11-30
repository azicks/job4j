package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void whenCheckDistanceFromPoint20ToPoint21Then1() {
        Point a = new Point(2, 0);
        assertThat(
                a.distanceTo(new Point(2, 1)),
                closeTo(1, 0.1));
    }
}