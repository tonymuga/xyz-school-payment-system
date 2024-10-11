package family.co.ke.xyz_payment.student.controllers;

import family.co.ke.xyz_payment.student.dtos.StudentRequest;
import family.co.ke.xyz_payment.student.dtos.ValidationResponse;
import family.co.ke.xyz_payment.student.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Validate student enrollment status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student is valid and enrolled",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Student not found or not enrolled",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/validate")
    public ResponseEntity<?> validateStudent(@RequestBody StudentRequest studentRequest) {
        boolean isEnrolled = studentService.isStudentEnrolled(studentRequest);
        if (isEnrolled) {
            return ResponseEntity.ok(new ValidationResponse("Success", true, "Student is valid and enrolled."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ValidationResponse("Failure", false, "Student not found or not enrolled."));
    }
}
