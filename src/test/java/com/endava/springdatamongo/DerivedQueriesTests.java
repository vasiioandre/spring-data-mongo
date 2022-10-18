package com.endava.springdatamongo;

import com.endava.springdatamongo.entity.Product;
import com.endava.springdatamongo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;

import static com.endava.springdatamongo.TestUtils.createProduct;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataMongoTest
public class DerivedQueriesTests {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void clean() {
        productRepository.deleteAll();
    }

    @Test
    void checkQueryFindByName() {
        final Product product = createProduct("peony", "12.5", "2022-09-21T14:27:47");
        final Product product2 = createProduct("roses", "20", "2022-10-21T14:27:47");
        final Product product3 = createProduct("lilies", "15", "2022-09-25T14:27:47");

        Iterable<Product> iterableProducts = Arrays.asList(product, product2, product3);
        productRepository.saveAll(iterableProducts);

        Product productResult = productRepository.findByName("roses");

        assertThat(productResult)
                .isEqualTo(product2);
    }

    @Test
    void checkQueryFindByNameOrEmail() {
        final Product product = createProduct("peony", "12.5", "2022-09-21T14:27:47");
        final Product product2 = createProduct("roses", "20", "2022-10-21T14:27:47");
        final Product product3 = createProduct("lilies", "15", "2022-09-25T14:27:47");

        Iterable<Product> iterableProducts = Arrays.asList(product, product2, product3);
        productRepository.saveAll(iterableProducts);

        List<Product> productList = productRepository.findByNameOrValue("roses", "12.5");

        assertThat(productList)
                .hasSize(2)
                .contains(product);
    }

    @Test
    void checkQueryFindByNameLike() {
        final Product product = createProduct("peony", "12.5", "2022-09-21T14:27:47");
        final Product product2 = createProduct("roses", "20", "2022-10-21T14:27:47");
        final Product product3 = createProduct("lilies", "15", "2022-09-25T14:27:47");
        final Product product4 = createProduct("lilies-of-the-valley", "25", "2022-05-25T14:27:47");

        Iterable<Product> iterableProducts = Arrays.asList(product, product2, product3, product4);
        productRepository.saveAll(iterableProducts);

        List<Product> productResult = productRepository.findByNameLike("lili*");

        assertThat(productResult)
                .hasSize(2)
                .contains(product4);
    }
}
