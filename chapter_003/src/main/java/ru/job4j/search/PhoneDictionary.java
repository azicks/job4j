package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        if (this.persons.size() > 0) {
            for (Person p : this.persons) {
                String personString = new StringBuilder().append(p.getName())
                        .append(p.getSurname())
                        .append(p.getAddress())
                        .append(p.getPhone()).toString();
                if (personString.contains(key)) {
                    result.add(p);
                }
            }
        }
        return result;
    }
}