package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;


public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    List<Faculty> findFacultiesByColor(String color);

    Faculty findByColor(String color);

    Faculty findByName(String name);
}

