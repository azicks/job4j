package ru.job4j.lambda;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LambdaTest {

    @Test
    public void whenAdd1Until3() {
        Lambda l = new Lambda();
        List<Double> buffer = new ArrayList<>();
        l.multiple2(
                0, 3, 1,
                (value, index) -> (double) value + index,
                buffer::add);
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }
}