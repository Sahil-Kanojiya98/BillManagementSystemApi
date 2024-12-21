package com.BillGenrationsSystem.BillManagementSystem.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Integer customerId;
    private Integer productId;
    private Integer quantity;
    private Boolean paymentStatus;

}
