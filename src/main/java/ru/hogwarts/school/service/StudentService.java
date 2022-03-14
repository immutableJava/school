package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NullStudentException;
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
            throw new NullStudentException();
        }
        return students.get(id);
    }

    public Student editStudent(Long id, Student student) {
        if (!students.containsKey(id)) {
            throw new NullStudentException();
        }
        students.put(id, student);
        return student;
    }

    public Student deleteStudent(Long id) {
        if (!students.containsKey(id)) {
            throw new NullStudentException();
        }
        return students.remove(id);
    }

    public List<Student> getFilteredStudentsByAge(int age) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                filteredStudents.add(student);
            }
        }
        if (filteredStudents.size() == 0) {
            throw new NullStudentException();
        }
        return filteredStudents;
    }

}
