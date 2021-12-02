package com.pichincha.caso.service;

import java.util.List;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Client;

public interface ClientService {

	List<Client> retrieveAllClients() throws ServiceException;

	Client retrieveClientById(Long id) throws ServiceException;

	Client save(Client client) throws ServiceException;

	void delete(Client client) throws ServiceException;

	Client retriveClientByIdentification(String identification) throws ServiceException;
}