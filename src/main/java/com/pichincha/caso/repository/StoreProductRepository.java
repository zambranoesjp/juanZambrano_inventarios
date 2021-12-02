package com.pichincha.caso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.caso.model.Store;
import com.pichincha.caso.model.StoreProduct;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
	List<StoreProduct> findByStore(Store store);
}