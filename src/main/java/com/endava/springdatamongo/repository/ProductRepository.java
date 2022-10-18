package com.endava.springdatamongo.repository;

import com.endava.springdatamongo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
    Product findByName(String name);

    Page<Product> findByName(String name, Pageable pageable);

    List<Product> findByNameOrValue(String name, String value);

    List<Product> findByNameLike(String name);
}
