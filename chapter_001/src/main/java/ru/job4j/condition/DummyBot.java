package ru.job4j.condition;

/**
 * @author Dmitrii Kozulin
 * @version $Id$
 * @since 29.11.2018
 */
public class DummyBot {

    /**
     * Отвечает на вопросы.
     *
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            rsl = "Привет, умник.";
        } else if (question.equals("Пока.")) {
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}