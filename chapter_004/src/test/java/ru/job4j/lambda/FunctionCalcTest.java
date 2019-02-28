package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FunctionCalcTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionCalc function = new FunctionCalc();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenBiFunctionThenLinearResults() {
        FunctionCalc function = new FunctionCalc();
        List<Double> result = function.diapason(1, 4, x -> 2 * Math.pow(x, 2) - 3 * x + 1);
        List<Double> expected = Arrays.asList(0D, 3D, 10D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLinearResults() {
        FunctionCalc function = new FunctionCalc();
        List<Double> result = function.diapason(1, 3, Math::log);
        List<Double> expected = Arrays.asList(0D, Math.log(2));
        assertThat(result, is(expected));
    }
}
