package com.pichincha.caso.to;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductFullTO {

	@NotNull
	public StoreTO store;

	@NotNull
	public List<ProductIdAndNameTO> products;
}