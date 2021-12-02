package com.pichincha.caso.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Product;
import com.pichincha.caso.repository.ProductRepository;
import com.pichincha.caso.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	void shouldGetAListOfProducts() {
		Product product1 = new Product(1L);
		product1.setName("Producto 1");

		Product product2 = new Product(2L);
		product2.setName("Producto 2");

		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);

		when(productRepository.findAll()).thenReturn(products);

		List<Product> resultado = productService.retrieveAllProducts();

		assertFalse(resultado.isEmpty());
	}

	@Test
	void shouldGetAProduct() {
		Product product = new Product(1L);
		product.setName("Tienda 1");

		Optional<Product> optinal = Optional.ofNullable(product);

		when(productRepository.findById(1L)).thenReturn(optinal);

		Product resultado = productService.retrieveProductByIdProduct(1L);

		assertNotNull(resultado);
	}

	@Test
	void shouldThrowException() {
		when(productRepository.findById(1L)).thenThrow(ServiceException.class);
	}
}