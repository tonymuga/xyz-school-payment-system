package family.co.ke.xyz_payment.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private String status;
    private boolean isEnrolled;
    private String message;
}
