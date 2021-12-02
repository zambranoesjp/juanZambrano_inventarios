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
import com.pichincha.caso.model.Client;
import com.pichincha.caso.repository.ClientRepository;
import com.pichincha.caso.service.impl.ClientServiceImpl;

@SpringBootTest
public class ClientServiceTest {

	@InjectMocks
	private ClientServiceImpl clientService;

	@Mock
	private ClientRepository clientRepository;

	@Test
	void shouldGetAListOfClients() {
		Client client1 = new Client(1L);
		client1.setName("Cliente 1");

		Client client2 = new Client(2L);
		client2.setName("Cliente 2");

		List<Client> clients = new ArrayList<>();
		clients.add(client1);
		clients.add(client2);

		when(clientRepository.findAll()).thenReturn(clients);

		List<Client> resultado = clientService.retrieveAllClients();

		assertFalse(resultado.isEmpty());
	}

	@Test
	void shouldGetAClient() {
		Client client = new Client(1L);
		client.setName("Tienda 1");

		Optional<Client> optinal = Optional.ofNullable(client);

		when(clientRepository.findById(1L)).thenReturn(optinal);

		Client resultado = clientService.retrieveClientById(1L);

		assertNotNull(resultado);
	}

	@Test
	void shouldThrowException() {
		when(clientRepository.findById(1L)).thenThrow(ServiceException.class);
	}
}