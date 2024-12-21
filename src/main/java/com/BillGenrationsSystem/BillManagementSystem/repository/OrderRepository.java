package com.BillGenrationsSystem.BillManagementSystem.repository;

import com.BillGenrationsSystem.BillManagementSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> { }
