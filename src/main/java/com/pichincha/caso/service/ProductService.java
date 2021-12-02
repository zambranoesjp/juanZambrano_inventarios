package com.pichincha.caso.service;

import java.util.List;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Product;

public interface ProductService {

	List<Product> retrieveAllProducts() throws ServiceException;

	Product retrieveProductByIdProduct(Long id) throws ServiceException;

	Product save(Product product) throws ServiceException;

	void delete(Product product) throws ServiceException;
}