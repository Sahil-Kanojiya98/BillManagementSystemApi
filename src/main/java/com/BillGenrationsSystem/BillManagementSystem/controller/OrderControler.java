package com.BillGenrationsSystem.BillManagementSystem.controller;
import com.BillGenrationsSystem.BillManagementSystem.model.Bill;
import com.BillGenrationsSystem.BillManagementSystem.model.dto.OrderRequestDTO;
import com.BillGenrationsSystem.BillManagementSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderControler {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO order) {
        try {
            Bill bill = orderService.placeOrder(order);
            return ResponseEntity.accepted().body(bill);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error placing order: " + e.getMessage());
        }
    }

    @PostMapping("/place-all")
    public ResponseEntity<?> placeOrder(@RequestBody List<OrderRequestDTO> orders) {
        try {
            orderService.placeOrders(orders);
            return ResponseEntity.ok("All Orders Placed Successfully");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error placing order: " + e.getMessage());
        }
    }

}
