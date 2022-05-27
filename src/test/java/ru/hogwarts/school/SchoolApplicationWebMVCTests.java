package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class SchoolApplicationWebMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FacultyService facultyService;

    @Test
    public void testGetFaculty() throws Exception {
        Long id = 1L;
        String name = "Gryffindor";
        String color = "red-yellow";
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);

        Faculty createdFaculty = new Faculty();
        createdFaculty.setId(id);
        createdFaculty.setName(name);
        createdFaculty.setColor(color);

        when(facultyService.getFaculty(id)).thenReturn(createdFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testAddFaculty() throws Exception {
        Long id = 1L;
        String name = "Gryffindor";
        String color = "red-yellow";
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);

        Faculty createdFaculty = new Faculty();
        createdFaculty.setId(id);
        createdFaculty.setName(name);
        createdFaculty.setColor(color);

        when(facultyService.addFaculty(newFaculty)).thenReturn(createdFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty")
                .content(mapper.writeValueAsString(newFaculty))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        String name = "Slytherin";
        String color = "green-white";
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);
        facultyService.addFaculty(newFaculty);
        facultyService.deleteFaculty(1L);
        Mockito.verify(facultyService, times(1)).deleteFaculty(1L);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/faculty/1", "newFaculty"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditFaculty() throws Exception {
        String name = "Slytherin";
        String color = "green-white";
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty", "newFaculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newFaculty)));
        newFaculty.setName("Gryffindor");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/faculty", "newFaculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newFaculty)))
                .andExpect(status().isOk());
    }


    @Test
    public void testFindFacultiesByColor() throws Exception {
        String firstFacultyName = "Slytherin";
        String firstFacultyColor = "green-white";

        String secondFacultyName = "Gryffindor";
        String secondFacultyColor = "red-yellow";

        String thirdFacultyName = "test";
        String thirdFacultyColor = "red-yellow";

        Faculty firstFaculty = new Faculty();
        Faculty secondFaculty = new Faculty();
        Faculty thirdFaculty = new Faculty();

        firstFaculty.setName(firstFacultyName);
        firstFaculty.setColor(firstFacultyColor);

        secondFaculty.setName(secondFacultyName);
        secondFaculty.setColor(secondFacultyColor);

        thirdFaculty.setName(thirdFacultyName);
        thirdFaculty.setColor(thirdFacultyColor);

        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(secondFaculty);
        facultyList.add(thirdFaculty);

        when(facultyService.findFacultiesByColor("red-yellow")).thenReturn(facultyList);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty", "facultyList")
                .param("color", secondFacultyColor)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
