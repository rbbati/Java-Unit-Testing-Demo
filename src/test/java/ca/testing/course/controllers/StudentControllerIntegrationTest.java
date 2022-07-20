package ca.testing.course.controllers;

import ca.testing.course.models.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class StudentControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void givenStudents_WhenGetStudents_thenReturnListOfStudents() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get("/students/list")
                ).andExpect(status().isOk())
                .andReturn().getResponse();

        List<Student> studentList = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Student>>() {
        });

        Assertions.assertEquals(2, studentList.size());
    }
}