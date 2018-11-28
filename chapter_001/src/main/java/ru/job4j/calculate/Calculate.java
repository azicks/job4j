package ru.job4j.calculate;

/**
* Класс, описывающий Hello world.
* @author Dmitriy Kozulin
* @since 28.11.2016
*/
public class Calculate
{
	/**
	* Метод main
	* @param args console input arguments
	*/
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

	/**
	* @param name Your name.
	* @return Echo plus name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo: " + name;
	}
}