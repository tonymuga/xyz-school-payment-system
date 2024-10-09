package family.co.ke.xyz_payment.student.entities;

import family.co.ke.xyz_payment.payment.entities.Payment;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "full_name")
    private String fullName;

    @Column(name = "enrollment_number", unique = true)
    private String enrollmentNumber;

    @Column(name = "is_enrolled")
    private boolean isEnrolled;

    @OneToMany
    private List<Payment> payments;
}
