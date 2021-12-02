package com.pichincha.caso.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ReportMontoVentaDTO {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME_STORE")
	private String storeName;

	@Column(name = "NAME_PRODUCT")
	private String productName;

	@Column(name = "QUANTITY")
	private Integer quantity;
}
