package com.BillGenrationsSystem.BillManagementSystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockRequestDto {

    private String productName;

    private String productDescription;

    private  Double price;

    private Double gstRate;

    private Integer stockQuantity;

    private  Integer thresholdLevel;

}
