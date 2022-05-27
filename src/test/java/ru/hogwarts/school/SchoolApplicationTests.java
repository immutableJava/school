package ru.hogwarts.school;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }


    @Test
    public void testGetStudent() throws Exception {

                assertThat(restTemplate.getForObject("http://localhost:" + port + "/student", Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsBetweenAge() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/student/getBetweenAge", Collections.class))
                .isNotNull();
    }

    @Test
    public void testAddStudent() throws Exception {
        Student student = new Student();
        student.setName("Joseph Williams");
        student.setId(1L);
        student.setAge(14);
        assertThat(restTemplate.postForObject("http://localhost:" + port + "/student/", student, Student.class))
                .isNotNull();
    }

    @Test
    public void testEditStudent() throws Exception {
        Student student = new Student();
        student.setName("Joseph Williams");
        student.setId(1L);
        student.setAge(14);
        HttpEntity<Student> entity = new HttpEntity<>(student);
        ResponseEntity<Student> response =
                restTemplate.exchange("http://localhost:" + port + "/student/", HttpMethod.PUT, entity, Student.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 1);
        assertEquals(response.getBody().getName(), "Joseph Williams");
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setName("Joseph Williams");
        student.setId(1L);
        student.setAge(14);
        HttpEntity<Student> entity = new HttpEntity<>(student);
        ResponseEntity<Student> response =
                restTemplate.exchange("http://localhost:" + port + "/student/", HttpMethod.DELETE, entity, Student.class);
        assertNull(response.getBody().getName());
    }

}
