package com.BillGenrationsSystem.BillManagementSystem.repository;

import com.BillGenrationsSystem.BillManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> { }
