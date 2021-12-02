package com.pichincha.caso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pichincha.caso.model.Product;
import com.pichincha.caso.repository.ProductRepository;
import com.pichincha.caso.service.CompensationService;
import com.pichincha.caso.to.Compensation;

@Service
public class CompensationServiceImpl implements CompensationService {

	private static final String URL05 = "https://mocki.io/v1/8778e0ce-e7c9-4fb3-a526-12bbe2829ec3";

	private static final String URL10 = "https://mocki.io/v1/31a3f88b-632a-421e-9dff-d6a6e3a73ea4";

	@Autowired
	private ProductRepository productRepository;

	@Async
	@Override
	public void function05(Product product) {
		RestTemplate restTemplate = new RestTemplate();
		extracted(product, restTemplate, URL05);
	}

	@Override
	public void function10(Product product) {
		RestTemplate restTemplate = new RestTemplate();
		extracted(product, restTemplate, URL10);
	}

	private void extracted(Product product, RestTemplate restTemplate, String url) {
		ResponseEntity<Compensation> response = restTemplate.getForEntity(url, Compensation.class);
		Compensation compensation = response.getBody();

		int cant = product.getStock().intValue();
		int stock = compensation.getStock().intValue();

		cant = -(cant) + stock;

		product.setStock(cant);

		productRepository.save(product);
	}
}
