package family.co.ke.xyz_payment.student.controller;

import family.co.ke.xyz_payment.student.controllers.StudentController;
import family.co.ke.xyz_payment.student.dtos.StudentRequest;
import family.co.ke.xyz_payment.student.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentRequest validStudentRequest;
    private StudentRequest invalidStudentRequest;

    @BeforeEach
    public void setUp() {
        validStudentRequest = new StudentRequest();
        validStudentRequest.setStudentId(12345l);
        validStudentRequest.setFullName("John");
        validStudentRequest.setEnrollmentNumber("12345343534343");

        invalidStudentRequest = new StudentRequest();
        invalidStudentRequest.setStudentId(54321l);
        invalidStudentRequest.setFullName("Jane");
        invalidStudentRequest.setEnrollmentNumber("Smith");
    }

    @Test
    public void testValidateStudent_Enrolled() throws Exception {
        // Mock the studentService to return true (student is enrolled)
        Mockito.when(studentService.isStudentEnrolled(any(StudentRequest.class)))
                .thenReturn(true);

        mockMvc.perform(post("/api/student/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validStudentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.enrolled").value(true))
                .andExpect(jsonPath("$.message").value("Student is valid and enrolled."));
    }

    @Test
    public void testValidateStudent_NotEnrolled() throws Exception {
        // Mock the studentService to return false (student is not enrolled)
        Mockito.when(studentService.isStudentEnrolled(any(StudentRequest.class)))
                .thenReturn(false);

        mockMvc.perform(post("/api/student/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidStudentRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("Failure"))
                .andExpect(jsonPath("$.enrolled").value(false))
                .andExpect(jsonPath("$.message").value("Student not found or not enrolled."));
    }

    @Test
    public void testValidateStudent_InvalidRequest() throws Exception {
        // Test invalid request with missing fields (e.g., empty studentId)
        StudentRequest incompleteRequest = new StudentRequest();
        incompleteRequest.setFullName("Invalid");

        mockMvc.perform(post("/api/student/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incompleteRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("Failure"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void testValidateStudent_ServiceException() throws Exception {
        // Mock the studentService to throw an exception
        Mockito.when(studentService.isStudentEnrolled(any(StudentRequest.class)))
                .thenThrow(new RuntimeException("Service exception"));

        mockMvc.perform(post("/api/student/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validStudentRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value("Error"))
                .andExpect(jsonPath("$.message").value("An error occurred while processing the request."));
    }
}

