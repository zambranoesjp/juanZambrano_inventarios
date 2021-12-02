package com.pichincha.caso.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.caso.model.Store;
import com.pichincha.caso.repository.StoreRepository;
import com.pichincha.caso.service.impl.StoreServiceImpl;

@SpringBootTest
public class StoreServiceTest {

	@InjectMocks
	private StoreServiceImpl storeService;

	@Mock
	private StoreRepository storeRepository;

	@Test
	void contextLoads() {
		Store store1 = new Store(1L);
		store1.setName("Tienda 1");

		Store store2 = new Store(2L);
		store2.setName("Tienda 2");

		List<Store> stores = new ArrayList<>();
		stores.add(store1);
		stores.add(store2);

		when(storeRepository.findAll()).thenReturn(stores);

		List<Store> resultado = storeService.retrieveAllStores();

		assertNotNull(resultado);
	}
}
