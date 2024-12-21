package com.BillGenrationsSystem.BillManagementSystem.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate odrerDate;

    private Integer productCount;

    private Double totalAmount;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Bill bill;

    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Payment payment;

}