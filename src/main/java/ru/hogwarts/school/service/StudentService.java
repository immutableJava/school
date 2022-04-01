package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;


public interface StudentService {

    Student addStudent(Student student);

    Student getStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    List<Student> findByAge(int age);

    List<Student> getStudentBetweenAge(int min, int max);

}
