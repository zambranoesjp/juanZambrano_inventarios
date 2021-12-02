package com.pichincha.caso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pichincha.caso.exception.ServiceException;
import com.pichincha.caso.model.Product;
import com.pichincha.caso.model.Store;
import com.pichincha.caso.model.StoreProduct;
import com.pichincha.caso.repository.ProductRepository;
import com.pichincha.caso.repository.StoreProductRepository;
import com.pichincha.caso.repository.StoreRepository;
import com.pichincha.caso.service.StoreService;
import com.pichincha.caso.to.ProductIdAndNameTO;
import com.pichincha.caso.to.StoreProductFullTO;
import com.pichincha.caso.to.StoreProductTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("storeServiceImpl")
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreProductRepository storeProductRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Store> retrieveAllStores() throws ServiceException {
		try {
			return storeRepository.findAll();
		} catch (DataAccessException e) {
			log.error("retrieveAllDistricts = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Store retrieveStoreById(Long idStore) throws ServiceException {
		try {
			return storeRepository.findById(idStore).get();
		} catch (Exception e) {
			log.error("retrieveDistrictById = {} ", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public StoreProductFullTO assignProducts(StoreProductTO storeProductTO) {

		Store store = storeRepository.findById(storeProductTO.getIdStore()).get();

		List<StoreProduct> details = storeProductRepository.findByStore(store);

		details.forEach(storeProduct -> storeProductRepository.delete(storeProduct));

		storeProductTO.getListIdProducts().forEach(idProduct -> assign(store, idProduct));

		return retriveByIdStore(store.getIdStore());
	}

	private void assign(Store store, Long idProduct) {
		Product product = productRepository.findById(idProduct).get();

		StoreProduct storeProduct = new StoreProduct();
		storeProduct.setStore(store);
		storeProduct.setProduct(product);

		storeProductRepository.save(storeProduct);
	}

	@Override
	@Transactional(readOnly = true)
	public StoreProductFullTO retriveByIdStore(Long idStore) {
		Store store = storeRepository.findById(idStore).get();

		List<StoreProduct> details = storeProductRepository.findByStore(store);

		List<ProductIdAndNameTO> productos = new ArrayList<>();

		details.forEach(storeProduct -> productos.add(storeProduct.getProduct().getIdAndName()));

		return new StoreProductFullTO(store.getStoreTo(), productos);
	}
}