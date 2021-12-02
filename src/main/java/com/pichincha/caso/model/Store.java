package com.pichincha.caso.model;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pichincha.caso.to.StoreTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STORE")
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;

	public Store(Long idStore) {
		this.idStore = idStore;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STORE")
	private Long idStore;

	@Column(name = "NAME")
	private String name;

	@Transient
	public StoreTO getStoreTo() {
		return StoreTO.builder().id(getIdStore()).name(getName()).build();
	}
}