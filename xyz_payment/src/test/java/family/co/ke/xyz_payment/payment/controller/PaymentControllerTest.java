package family.co.ke.xyz_payment.payment.controller;


import family.co.ke.xyz_payment.payment.controllers.PaymentController;
import family.co.ke.xyz_payment.payment.dtos.PaymentRequest;
import family.co.ke.xyz_payment.payment.services.PaymentService;
import family.co.ke.xyz_payment.student.dtos.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    private PaymentRequest validPaymentRequest;
    private PaymentRequest invalidPaymentRequest;

    @BeforeEach
    public void setUp() {
        validPaymentRequest = new PaymentRequest();
        validPaymentRequest.setAmount(1000.0);
        validPaymentRequest.setStudentId(67890l);
        validPaymentRequest.setPaymentDate(LocalDateTime.now());

        invalidPaymentRequest = new PaymentRequest();
        invalidPaymentRequest.setAmount(-100.0); // Invalid amount
        invalidPaymentRequest.setStudentId(null);  // Missing studentId
    }

    @Test
    public void testProcessPayment_Success() throws Exception {
        // No need to mock specific behavior as the service does not return anything for now.
        Mockito.doNothing().when(paymentService).savePayment(any(PaymentRequest.class));

        mockMvc.perform(post("/api/payment/notify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validPaymentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Payment notification received and processed."));
    }

    @Test
    public void testProcessPayment_InvalidRequest() throws Exception {
        // Here we simulate an invalid request being made.
        mockMvc.perform(post("/api/payment/notify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPaymentRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testProcessPayment_ServiceThrowsException() throws Exception {
        // Mock the service to throw an exception when savePayment is called
        Mockito.doThrow(new RuntimeException("Service exception"))
                .when(paymentService).savePayment(any(PaymentRequest.class));

        mockMvc.perform(post("/api/payment/notify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validPaymentRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value("Error"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("An error occurred while processing the payment."));
    }
}
