package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long id = 0;

    public Student addStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    public Student getStudent(Long id) {
        if (!students.containsKey(id)) {
            return null;
        }
        return students.get(id);
    }

    public Student editStudent(Long id, Student student) {
        students.put(id, student);
        return student;
    }

    public Student deleteStudent(Long id) {
        if (!students.containsKey(id)) {
            return null;
        }
        return students.remove(id);
    }

    public List<Student> getSortedStudentsByAge(int age) {
        List<Student> sortedStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                sortedStudents.add(student);
            }
        }
        if (sortedStudents.size() == 0) {
            return null;
        }
        return sortedStudents;
    }

}
