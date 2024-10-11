package family.co.ke.xyz_payment.payment.services;

import family.co.ke.xyz_payment.payment.dtos.PaymentRequest;
import family.co.ke.xyz_payment.payment.entities.Payment;
import family.co.ke.xyz_payment.payment.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {


    private  final PaymentRepository paymentRepository;


    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void savePayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setTransactionId(paymentRequest.getTransactionId());
        payment.setStudentId(paymentRequest.getStudentId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentDate(paymentRequest.getPaymentDate());

        paymentRepository.save(payment);
    }
}
