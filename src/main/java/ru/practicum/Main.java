package ru.practicum;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Student, Integer> studentIntegerMap = new HashMap<>();
        Student a = new Student(1,"1asd");
        Student s = new Student(2,"2asd");
        Student b = new Student(3,"3asd");
        Student n = new Student(5,"5asd");
        studentIntegerMap.put(a, 10);
        studentIntegerMap.put(s, 15);
        studentIntegerMap.put(b, 13);
        studentIntegerMap.put(n, 12);

        studentIntegerMap.get(a);
        System.out.println(studentIntegerMap.get(b));
    }
}