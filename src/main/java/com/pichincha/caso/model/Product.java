package com.pichincha.caso.model;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pichincha.caso.to.ProductCodeAndNameTO;
import com.pichincha.caso.to.ProductIdAndNameTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public Product(Long idProduct) {
		this.idProduct = idProduct;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCT")
	private Long idProduct;

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "STOCK")
	private Integer stock;

	@Transient
	public ProductIdAndNameTO getIdAndName() {
		return ProductIdAndNameTO.builder().id(getIdProduct()).name(getName()).build();
	}
	
	@Transient
	public ProductCodeAndNameTO getCodeAndName() {
		return ProductCodeAndNameTO.builder().code(getCode()).name(getName()).build();
	}
}