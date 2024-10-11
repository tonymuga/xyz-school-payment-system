package family.co.ke.xyz_payment.payment.entities;

import family.co.ke.xyz_payment.student.entities.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @Column(unique = true, nullable = false)
    private Long studentId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

}

