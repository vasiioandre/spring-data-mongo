package com.endava.springdatamongo.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private String value;
    private LocalDateTime payedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(LocalDateTime payedAt) {
        this.payedAt = payedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && name.equals(product.name) && value.equals(product.value) && payedAt.equals(product.payedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, payedAt);
    }
}
