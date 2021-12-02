package com.pichincha.caso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Product;
import com.pichincha.caso.repository.ProductRepository;
import com.pichincha.caso.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository ProductRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> retrieveAllProducts() throws ServiceException {
		try {
			return ProductRepository.findAll();
		} catch (DataAccessException e) {
			log.error("retrieveAllDistricts = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Product retrieveProductByIdProduct(Long idProduct) throws ServiceException {
		try {
			return ProductRepository.findById(idProduct).get();
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Product save(Product product) throws ServiceException {
		try {
			return ProductRepository.save(product);
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void delete(Product product) throws ServiceException {
		try {
			ProductRepository.delete(product);
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
}