package com.pichincha.caso.to;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCodeAndNameTO {
	@NotNull(message = "code is required")
	private String code;

	@NotEmpty(message = "name is required")
	@NotNull(message = "name is required")
	private String name;
}