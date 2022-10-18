package com.endava.springdatamongo;

import com.endava.springdatamongo.entity.Product;

import java.time.LocalDateTime;

public class TestUtils {
    public static Product createProduct(String name, String value, String payedAt) {
        final Product product = new Product();
        product.setName(name);
        product.setValue(value);
        product.setPayedAt(LocalDateTime.parse(payedAt));

        return product;
    }
}
