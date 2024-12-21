package com.BillGenrationsSystem.BillManagementSystem.service;


import com.BillGenrationsSystem.BillManagementSystem.model.Customer;
import com.BillGenrationsSystem.BillManagementSystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

}
