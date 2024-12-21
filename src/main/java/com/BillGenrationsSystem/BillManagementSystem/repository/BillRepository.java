package com.BillGenrationsSystem.BillManagementSystem.repository;


import com.BillGenrationsSystem.BillManagementSystem.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public  interface BillRepository extends JpaRepository<Bill,Integer> {

    List<Bill> findByBillDate(LocalDate today);

}

