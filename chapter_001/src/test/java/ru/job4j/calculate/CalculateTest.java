package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Dmitiy Kozulin
* @version $Id$
* @since 28.11.2018
*/
public class CalculateTest
{	
	/**
	* Test echo.
	*/
	@Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "InputName";
		String expect = "Echo, echo, echo: InputName"; 
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	} 
}
 