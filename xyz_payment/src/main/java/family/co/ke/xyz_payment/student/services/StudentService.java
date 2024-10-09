package family.co.ke.xyz_payment.student.services;

import family.co.ke.xyz_payment.student.repositories.StudentRepository;
import family.co.ke.xyz_payment.student.dtos.StudentRequest;
import family.co.ke.xyz_payment.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    private final StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean isStudentEnrolled(StudentRequest request) {
        Student student=  studentRepository.findByIdAndEnrollmentNumber(request.getStudentId(), request.getEnrollmentNumber());
                if(student==null){
                    return false;
                }
                else return student.isEnrolled();
    }
}

