package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SortUser {
    public Set<User> sort(List<User> u) {
        return new TreeSet<>(u);
    }

    public List<User> sortNameLength(List<User> list) {
        return list.stream().sorted(Comparator.comparingInt(user -> user.getName().length()))
                .collect(Collectors.toList());
    }

    public List<User> sortByAllFields(List<User> list) {
        return list.stream().sorted(
                Comparator.comparing(User::getName).thenComparingInt(User::getAge))
                .collect(Collectors.toList());
    }
}
