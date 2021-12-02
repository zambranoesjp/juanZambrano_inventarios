package com.pichincha.caso.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	public OrderDetail(Order order, Store store, Product product, Integer quantity) {
		super();
		this.order = order;
		this.store = store;
		this.product = product;
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDER_DETAIL")
	private Long idOrderDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORDER")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STORE")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUCT")
	private Product product;

	@Column(name = "QUANTITY")
	private Integer quantity;
}