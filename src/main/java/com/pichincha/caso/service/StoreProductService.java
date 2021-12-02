package com.pichincha.caso.service;

import java.util.List;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Store;
import com.pichincha.caso.model.StoreProduct;

public interface StoreProductService {

	List<StoreProduct> findByStore(Store store) throws ServiceException;

}