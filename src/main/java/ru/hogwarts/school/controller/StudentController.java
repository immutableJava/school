package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.NullStudentException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            throw new NullStudentException();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/getBetweenAge")
    public ResponseEntity<List<Student>> getStudentsBetweenAge(@RequestParam int minAge, @RequestParam int maxAge) {
        List<Student> students = studentService.getStudentBetweenAge(minAge, maxAge);
        if (students == null) {
            throw new NullStudentException();
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<Student>> findByAge(@RequestParam int age) {
        List<Student> students = studentService.findByAge(age);
        return ResponseEntity.ok(students);
    }
}
