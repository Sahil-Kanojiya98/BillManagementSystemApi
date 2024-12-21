package com.BillGenrationsSystem.BillManagementSystem.repository;

import com.BillGenrationsSystem.BillManagementSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> { }