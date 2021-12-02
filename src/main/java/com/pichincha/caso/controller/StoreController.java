package com.pichincha.caso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Store;
import com.pichincha.caso.service.StoreService;
import com.pichincha.caso.to.StoreProductFullTO;
import com.pichincha.caso.to.StoreProductTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/inventory/store")
public class StoreController {

	@Autowired
	@Qualifier("storeServiceImpl")
	private StoreService storeService;

	@GetMapping("/all")
	public ResponseEntity<?> retrieveAllClients() {
		try {
			List<Store> stores = storeService.retrieveAllStores();

			log.info(String.format("Stores count: %s", stores.size()));

			return new ResponseEntity<>(stores, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/assignProducts")
	public ResponseEntity<?> assignProducts(@RequestBody StoreProductTO storeProductTO) {
		try {
			StoreProductFullTO result = storeService.assignProducts(storeProductTO);
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/assignedProducts/{idStore}")
	public ResponseEntity<?> retriveByIdStore(@PathVariable("idStore") Long idStore) {
		try {
			StoreProductFullTO result = storeService.retriveByIdStore(idStore);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}