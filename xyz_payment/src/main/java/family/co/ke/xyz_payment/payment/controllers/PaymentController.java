package family.co.ke.xyz_payment.payment.controllers;

import family.co.ke.xyz_payment.payment.dtos.PaymentRequest;
import family.co.ke.xyz_payment.payment.services.PaymentService;
import family.co.ke.xyz_payment.student.dtos.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    private final PaymentService paymentService;


    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/notify")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.savePayment(paymentRequest);
        return ResponseEntity.ok(new ApiResponse("Success", true, "Payment notification received and processed."));
    }
}

