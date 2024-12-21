package com.BillGenrationsSystem.BillManagementSystem.controller;

import com.BillGenrationsSystem.BillManagementSystem.model.Customer;
import com.BillGenrationsSystem.BillManagementSystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        try {
            return ResponseEntity.accepted().body(customerService.save(customer));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error: " + e.getMessage());
        }
    }

}
