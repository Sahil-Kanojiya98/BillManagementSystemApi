package com.BillGenrationsSystem.BillManagementSystem.controller;

import com.BillGenrationsSystem.BillManagementSystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/{orderId}")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PutMapping
    public ResponseEntity<?> paymentProcess(@PathVariable("orderId") Integer orderId) {
        try {
            paymentService.makePayment(orderId);
            return ResponseEntity.accepted().body("payment success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("payment error: " + e.getMessage());
        }
    }

}