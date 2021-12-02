package com.pichincha.caso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Store;
import com.pichincha.caso.model.StoreProduct;
import com.pichincha.caso.repository.StoreProductRepository;
import com.pichincha.caso.service.StoreProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("storeProductServiceImpl")
public class StoreProductServiceImpl implements StoreProductService {

	@Autowired
	private StoreProductRepository storeProductRepository;

	@Override
	@Transactional(readOnly = true)
	public List<StoreProduct> findByStore(Store store) throws ServiceException {
		try {
			return storeProductRepository.findByStore(store);
		} catch (DataAccessException e) {
			log.error("retrieveAllDistricts = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
}