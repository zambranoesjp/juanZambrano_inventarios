package com.pichincha.caso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Client;
import com.pichincha.caso.repository.ClientRepository;
import com.pichincha.caso.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("clientServiceImpl")
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Client> retrieveAllClients() throws ServiceException {
		try {
			return clientRepository.findAll();
		} catch (DataAccessException e) {
			log.error("retrieveAllDistricts = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Client retrieveClientById(Long idCliente) throws ServiceException {
		try {
			return clientRepository.findById(idCliente).get();
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Client save(Client client) throws ServiceException {
		try {
			return clientRepository.save(client);
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void delete(Client client) throws ServiceException {
		try {
			clientRepository.delete(client);
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Client retriveClientByIdentification(String identification) throws ServiceException {
		try {
			return clientRepository.findByIdentification(identification).stream().findFirst()
					.orElseThrow(() -> new ServiceException("Client not found"));
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
}