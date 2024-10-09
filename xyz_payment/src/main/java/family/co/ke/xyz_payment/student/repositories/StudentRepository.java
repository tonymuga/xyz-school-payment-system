package family.co.ke.xyz_payment.student.repositories;

import family.co.ke.xyz_payment.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByIdAndEnrollmentNumber(Long studentId, String enrollmentNumber);
}
