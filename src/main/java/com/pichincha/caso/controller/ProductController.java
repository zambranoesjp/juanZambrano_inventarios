package com.pichincha.caso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Product;
import com.pichincha.caso.service.ProductService;
import com.pichincha.caso.to.ProductIdAndStockTO;
import com.pichincha.caso.to.ProductTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/inventory/product")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<?> retrieveAllProducts() {
		try {
			List<Product> products = productService.retrieveAllProducts();

			List<ProductTO> resultado = new ArrayList<>();

			products.forEach(p -> resultado.add(p.getProductoTO()));

			log.info(String.format("Products count: %s", products.size()));

			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateStock")
	public ResponseEntity<?> update(@Valid @RequestBody ProductIdAndStockTO to) {
		try {
			Product product = productService.retrieveProductByIdProduct(to.getId());
			product.setStock(to.getStock());

			log.info(String.format("Client updated: %s", product.toString()));

			return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}