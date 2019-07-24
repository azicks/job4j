package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {


    @Test
    public void testUser() {
        Map<User, Object> map = new HashMap<>();
        User u1 = new User("user", 1, new GregorianCalendar(1960, Calendar.MAY, 10));
        User u2 = new User("user", 1, new GregorianCalendar(1960, Calendar.MAY, 10));
        map.put(u1, null);
        map.put(u2, null);
        //System.out.println(map);
    }

}
