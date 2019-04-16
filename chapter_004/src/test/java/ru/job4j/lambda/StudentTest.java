package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StudentTest {

    @Test
    public void test() {
        Student s1 = new Student("1", 1);
        Student s2 = new Student("2", 5);
        Student s3 = new Student("3", 3);
        Student s4 = new Student("4", 5);
        Student s5 = new Student("5", 2);
        Student s6 = new Student("6", 4);
        Student s7 = new Student("7", 4);
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(1, null);
        students.add(3, null);
        assertThat(Student.levelOf(students, 3),
                is(List.of(s2, s4, s6, s7)));
    }
}