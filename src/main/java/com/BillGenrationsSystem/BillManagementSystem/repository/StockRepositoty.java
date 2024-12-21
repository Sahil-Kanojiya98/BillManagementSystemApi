package com.BillGenrationsSystem.BillManagementSystem.repository;

import com.BillGenrationsSystem.BillManagementSystem.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepositoty extends JpaRepository<Stock ,Integer> { }