package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void sortTest() {
        User u1 = new User(30, "30");
        User u2 = new User(15, "15");
        User u3 = new User(20, "20");
        User u4 = new User(12, "12");
        List<User> list = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
        TreeSet<User> set = (TreeSet<User>) new SortUser().sort(list);
        int[] expected = {12, 15, 20, 30};
        int[] result = new int[4];
        for (int i = 0; i != 4; i++) {
            result[i] = set.pollFirst().getAge();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void sortByNameLenghtTest() {
        User u1 = new User(25, "Сергей");
        User u2 = new User(30, "Иван");
        User u3 = new User(20, "Сергей");
        User u4 = new User(25, "Иван");
        List<User> list = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
        new SortUser().sortNameLength(list);
        String[] expected = {"Иван", "Иван", "Сергей", "Сергей"};
        String[] result = new String[4];
        for (int i = 0; i != 4; i++) {
            result[i] = list.get(i).getName();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void sortBy2FieldsTest() {
        User u1 = new User(25, "Сергей");
        User u2 = new User(30, "Иван");
        User u3 = new User(20, "Сергей");
        User u4 = new User(25, "Иван");
        List<User> list = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
        new SortUser().sortByAllFields(list);
        String[] expected = {"Иван25", "Иван30", "Сергей20", "Сергей25"};
        String[] result = new String[4];
        for (int i = 0; i != 4; i++) {
            result[i] = list.get(i).getName() + list.get(i).getAge();
        }
        assertThat(result, is(expected));
    }
}