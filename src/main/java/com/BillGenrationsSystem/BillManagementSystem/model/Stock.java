package com.BillGenrationsSystem.BillManagementSystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"product"})
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;

    private Integer stockQuantity;

    private  Integer thresholdLevel;

}
