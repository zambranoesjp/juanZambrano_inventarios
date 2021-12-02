package com.pichincha.caso.to;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductIdAndStockTO {

	@NotNull(message = "id is required")
	private Long id;

	@Min(value = 1, message = "stock cannot be less or equals than zero")
	private Integer stock;
}