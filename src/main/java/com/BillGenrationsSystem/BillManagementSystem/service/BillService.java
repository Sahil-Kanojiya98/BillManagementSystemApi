package com.BillGenrationsSystem.BillManagementSystem.service;

import com.BillGenrationsSystem.BillManagementSystem.model.Bill;
import com.BillGenrationsSystem.BillManagementSystem.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillService {

    public Bill generateBill(Order order,Boolean paidOrNot) {
        Integer productCount = order.getProductCount();
        Double price = order.getProduct().getPrice();
        Double billAmount = productCount*price;
        Double gstRate = order.getProduct().getGstRate();
        return Bill.builder()
                .order(order)
                .billAmount(billAmount)
                .billDate(LocalDate.now())
                .gstAmount(gstRate*billAmount/100)
                .netAmount(billAmount+(gstRate*billAmount/100))
                .paidornot(paidOrNot)
                .build();
    }

}