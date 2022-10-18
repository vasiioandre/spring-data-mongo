package com.endava.springdatamongo;

import com.endava.springdatamongo.entity.Product;
import com.endava.springdatamongo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static com.endava.springdatamongo.TestUtils.createProduct;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataMongoTest
public class PagingAndSortingTests {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void clean() {
        productRepository.deleteAll();
    }

    @Test
    void checkPagingAndSorting() {
        for(int i=0; i<20; i++) {
            productRepository.save(createProduct("roses" + i, "20", "2022-10-21T14:27:47"));
        }
        for(int i=0; i<20; i++) {
            productRepository.save(createProduct("peonies" + i, "10", "2022-10-21T14:27:47"));
        }

        final Page<Product> page = productRepository.findAll(PageRequest.of(0, 10));

        assertThat(page.getTotalPages()).isEqualTo(4);
        assertThat(page.getTotalElements()).isEqualTo(40);
    }

    @Test
    void findByNameWithPagingAndSorting() {
        for(int i=0; i<20; i++) {
            productRepository.save(createProduct("roses" + i, "20", "2022-10-21T14:27:47"));
        }
        for(int i=0; i<20; i++) {
            productRepository.save(createProduct("peonies" + i, "10", "2022-10-21T14:27:47"));
        }

        final Page<Product> page = productRepository.findByName("roses3", PageRequest.of(0, 10,
                Sort.by(Sort.Direction.ASC, "name")));

        assertThat(page.getContent())
                .extracting(Product::getName)
                .first()
                .isEqualTo("roses3");
    }
}
