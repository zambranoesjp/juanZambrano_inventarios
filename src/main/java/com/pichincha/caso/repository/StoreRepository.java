package com.pichincha.caso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.caso.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}