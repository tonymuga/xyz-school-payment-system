package family.co.ke.xyz_payment.payment.repositories;

import family.co.ke.xyz_payment.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
