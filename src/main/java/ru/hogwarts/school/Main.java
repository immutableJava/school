package ru.hogwarts.school;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Student> students= new ArrayList<>();
        List<Faculty> faculties = new ArrayList<>();
        Student firstStudent = new Student(1,"Harry Potter",13);
        Student secondStudent = new Student(2,"Draco Malfoy",13);
        Student thirdStudent = new Student(3,"Cedric Diggory",15);
        students.add(firstStudent);
        students.add(secondStudent);
        students.add(thirdStudent);
        System.out.println(students);
        Faculty firstFaculty = new Faculty(1,"Gryffindor","red and yellow");
        Faculty secondFaculty = new Faculty(2,"Slytherin","green and white");
        Faculty thirdFaculty = new Faculty(3,"Hufflepuff","yellow and black");
        faculties.add(firstFaculty);
        faculties.add(secondFaculty);
        faculties.add(thirdFaculty);
        System.out.println(faculties);
    }
}
