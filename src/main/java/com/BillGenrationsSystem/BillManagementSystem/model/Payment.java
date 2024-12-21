package com.BillGenrationsSystem.BillManagementSystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "order")
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;

    private LocalDate paymentDate;

    private Double paymentAmount;

    private Boolean paymentConfirmation;

}
