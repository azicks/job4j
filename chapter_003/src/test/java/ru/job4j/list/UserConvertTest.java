package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenListusersToHashMap() {
        HashMap<Integer, User> expect = new HashMap<>();
        List<User> list = new ArrayList<>();
        UserConvert c = new UserConvert();
        User user1 = new User(1, "name", "city");
        User user2 = new User(2, "name2", "city2");
        expect.put(1, user1);
        expect.put(2, user2);
        list.add(user1);
        list.add(user2);
        assertThat(c.process(list), is(expect));
    }
}
