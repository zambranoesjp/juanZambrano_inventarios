package com.pichincha.caso.to;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ProductsList {
	@NotEmpty
	private List<ProductTO> prods;

	public ProductsList() {
		prods = new ArrayList<>();
	}
}