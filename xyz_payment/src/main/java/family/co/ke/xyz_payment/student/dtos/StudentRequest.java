package family.co.ke.xyz_payment.student.dtos;


import lombok.Data;

@Data
public class StudentRequest {
    private Long studentId;
    private String fullName;
    private String enrollmentNumber;
}

