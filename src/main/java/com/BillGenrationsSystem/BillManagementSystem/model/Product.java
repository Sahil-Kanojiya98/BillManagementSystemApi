package com.BillGenrationsSystem.BillManagementSystem.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer productId;

    private String productName;

    private String productDescription;

    private  Double price;

    private Double gstRate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Stock stock;

}
