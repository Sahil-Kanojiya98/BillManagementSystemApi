package com.BillGenrationsSystem.BillManagementSystem.service;

import com.BillGenrationsSystem.BillManagementSystem.model.dto.ProductStockRequestDto;
import com.BillGenrationsSystem.BillManagementSystem.model.Product;
import com.BillGenrationsSystem.BillManagementSystem.model.Stock;
import com.BillGenrationsSystem.BillManagementSystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductStockRequestDto dto) {
        Product product=Product.builder()
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .gstRate(dto.getGstRate())
                .build();
        Stock stock=Stock.builder()
                .product(product)
                .stockQuantity(dto.getStockQuantity())
                .thresholdLevel(dto.getThresholdLevel())
                .build();
        product.setStock(stock);
        return productRepository.save(product);
    }

}
