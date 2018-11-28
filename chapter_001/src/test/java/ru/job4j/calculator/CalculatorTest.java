package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Мини калькулятор.
* @author Dmitriy Kozulin
* @since 28.11.2016
*/
public class CalculatorTest {

	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	@Test
	public void whenSubtOneFromTwoThenOne() {
		Calculator calc = new Calculator();
		calc.subtract(2D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}

	@Test
	public void whenMultOneOnTwoThenTwo() {
		Calculator calc = new Calculator();
		calc.multiply(1D, 2D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	@Test
	public void whenDivTwoByOneThenTwo() {
		Calculator calc = new Calculator();
		calc.divide(2D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
}