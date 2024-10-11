package family.co.ke.xyz_payment.payment.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String transactionId;
    private Long studentId;
    private Double amount;
    private LocalDateTime paymentDate;

}

