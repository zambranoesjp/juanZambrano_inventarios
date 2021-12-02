package com.pichincha.caso.model.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ReportNumTransDTO {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME_STORE")
	private String storeName;

	@Column(name = "DATE_ORDER")
	private LocalDate dateOrder;

	@Column(name = "QUANTITY")
	private Integer quantity;
}
