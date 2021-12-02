package com.pichincha.caso.service;

import java.util.List;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Store;
import com.pichincha.caso.to.StoreProductFullTO;
import com.pichincha.caso.to.StoreProductTO;

public interface StoreService {
	List<Store> retrieveAllStores() throws ServiceException;

	Store retrieveStoreById(Long idStore) throws ServiceException;

	StoreProductFullTO assignProducts(StoreProductTO storeProductTO);

	StoreProductFullTO retriveByIdStore(Long idStore);
}