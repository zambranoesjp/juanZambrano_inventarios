package com.pichincha.caso.to;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailTO {
	@NotNull
	private Long idStore;

	@NotNull
	private Long idProduct;

	@NotNull
	private Integer quantity;
}