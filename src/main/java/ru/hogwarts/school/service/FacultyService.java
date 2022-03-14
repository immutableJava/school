package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NullFacultyException;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long id = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        if (!faculties.containsKey(id)) {
            throw new NullFacultyException();
        }
        return faculties.get(id);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            throw new NullFacultyException();
        }
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id) {
        if (!faculties.containsKey(id)) {
            throw new NullFacultyException();
        }
        return faculties.remove(id);
    }

    public List<Faculty> getFilteredFacultiesByColor(String color) {
        List<Faculty> filteredFaculties = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                filteredFaculties.add(faculty);
            }
        }
        if (filteredFaculties.size() == 0) {
            throw new NullFacultyException();
        }
        return filteredFaculties;
    }
}

