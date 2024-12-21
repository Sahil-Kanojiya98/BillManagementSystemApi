package com.BillGenrationsSystem.BillManagementSystem.repository;


import com.BillGenrationsSystem.BillManagementSystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Integer> { }
