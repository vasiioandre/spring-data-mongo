package com.endava.springdatamongo;

import com.endava.springdatamongo.entity.Product;
import com.endava.springdatamongo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static com.endava.springdatamongo.TestUtils.createProduct;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataMongoTest
class SpringDataMongoApplicationTests {
	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	void clean() {
		productRepository.deleteAll();
	}

	@Test
	void checkProductIsSaved() {
		final Product product = new Product();
		product.setName("peony");
		product.setValue("12.5");
		product.setPayedAt(LocalDateTime.parse("2022-09-21T14:27:47"));

		productRepository.save(product);

		assertThat(productRepository.findAll())
				.hasSize(1)
				.first()
				.isEqualTo(product);
	}

	@Test
	void checkProductList() {
		final Product product = createProduct("peony", "12.5", "2022-09-21T14:27:47");
		final Product product2 = createProduct("roses", "20", "2022-10-21T14:27:47");
		final Product product3 = createProduct("lilies", "15", "2022-09-25T14:27:47");

		Iterable<Product> iterableProducts = Arrays.asList(product, product2, product3);
		productRepository.saveAll(iterableProducts);

		assertThat(productRepository.findAll())
				.hasSize(3);
	}

	@Test
	void checkDeleteProduct() {
		final Product product = createProduct("peony", "12.5", "2022-09-21T14:27:47");
		final Product product2 = createProduct("roses", "20", "2022-10-21T14:27:47");
		final Product product3 = createProduct("lilies", "15", "2022-09-25T14:27:47");

		Iterable<Product> iterableProducts = Arrays.asList(product, product2, product3);
		productRepository.saveAll(iterableProducts);

		//productRepository.delete(product2);
		productRepository.deleteById(product2.getId());

		assertThat(productRepository.findAll())
				.hasSize(2);
	}

}
