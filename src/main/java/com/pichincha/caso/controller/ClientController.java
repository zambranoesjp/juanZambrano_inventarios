package com.pichincha.caso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Client;
import com.pichincha.caso.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/inventory/client")
public class ClientController {

	@Autowired
	@Qualifier("clientServiceImpl")
	private ClientService clientService;

	@GetMapping("/all")
	public ResponseEntity<?> retrieveAllClients() {
		try {
			List<Client> clientes = clientService.retrieveAllClients();

			log.info(String.format("Clients count: %s", clientes.size()));

			return new ResponseEntity<List<Client>>(clientes, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/new")
	public ResponseEntity<?> save(@RequestBody Client client) {
		try {
			client = clientService.save(client);

			log.info(String.format("Client saved: %s", client.toString()));

			return new ResponseEntity<Client>(client, HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Client client) {
		try {
			client = clientService.save(client);

			log.info(String.format("Client updated: %s", client.toString()));

			return new ResponseEntity<Client>(client, HttpStatus.ACCEPTED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{idCliente}")
	public ResponseEntity<?> deleteByIdClient(@PathVariable("idCliente") Long idClient) {
		try {

			Client client = clientService.retrieveClientById(idClient);

			log.info(String.format("Client to delete: %s", client.toString()));

			clientService.delete(client);

			log.info(String.format("Client deleted!"));

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}