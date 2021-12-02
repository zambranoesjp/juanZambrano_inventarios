package com.pichincha.caso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pichincha.caso.to.ProductsList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StartApp {

	private static final String URL = "https://mocki.io/v1/1dadd5b8-4566-4010-9efc-69f30fd70631";

	@Autowired
	private ProductService produService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {
		log.info(" >> The program stars!");
		initializeProducts();
		initializeStores();
	}

	public void initializeProducts() {
		log.info(">> 1. Initializing products");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<ProductsList> response = restTemplate.getForEntity(URL, ProductsList.class);
		ProductsList products = response.getBody();

		products.getProds().forEach(productTO -> produService.save(productTO.getProducto()));
	}

	public void initializeStores() {
		log.info(">> 2. Initializing stores");

		List<String> sqlList = ListSqlFactory();

		sqlList.forEach(sql -> executeSql(sql));

	}

	private List<String> ListSqlFactory() {
		String sql1 = "INSERT INTO STORE (ID_STORE, NAME) VALUES (1, 'Tienda 1')";
		String sql2 = "INSERT INTO STORE (ID_STORE, NAME) VALUES (2, 'Tienda 2')";
		String sql3 = "INSERT INTO STORE (ID_STORE, NAME) VALUES (3, 'Tienda 3')";
		String sql4 = "INSERT INTO STORE (ID_STORE, NAME) VALUES (4, 'Tienda 4')";

		List<String> sqlList = new ArrayList<>();
		sqlList.add(sql1);
		sqlList.add(sql2);
		sqlList.add(sql3);
		sqlList.add(sql4);

		return sqlList;
	}

	private void executeSql(String sql) {
		try {
			log.info("SQL: " + sql);
			jdbcTemplate.execute(sql);
		} catch (DataAccessException e) {
			log.error("Error: " + e.getMessage());
		}
	}
}