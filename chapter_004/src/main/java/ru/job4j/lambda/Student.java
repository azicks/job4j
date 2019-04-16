package ru.job4j.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparable<Student> {
    private String name;
    private int scope;

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public int compareTo(Student o2) {
        return Integer.compare(o2.getScope(), this.getScope());
    }

    @Override
    public String toString() {
        return String.format("%s scope: %d", name, scope);
    }

    /**
     *  Метод возвращает список студентов у которых балл аттестата больше bound.
     *
     */
    static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted()
                .takeWhile(student -> student.getScope() > bound)
                .collect(Collectors.toList());
    }
}
