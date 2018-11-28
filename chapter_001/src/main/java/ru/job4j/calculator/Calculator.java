package ru.job4j.calculator;

/**
* Мини калькулятор.
* @author Dmitriy Kozulin
* @since 28.11.2016
*/
public class Calculator {

	private double result;

	/**
	 * Метод add
	 * @param first 1st arg
	 * @param second 2st arg
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 * Метод subtract
	 * @param first 1st arg
	 * @param second 2st arg
	 */
	public void subtract(double first, double second) {
		this.result = first - second;
	}
	/**
	 * Метод multiply
	 * @param first 1st arg
	 * @param second 2st arg
	 */
	public void multiply(double first, double second) {
		this.result = first * second;
	}
	/**
	 * Метод divide
	 * @param first 1st arg
	 * @param second 2st arg
	 */
	public void divide(double first, double second) {
		this.result = first / second;
	}

	/**
	 * getter
	*/
	public double getResult() {
		return this.result;
	}
}