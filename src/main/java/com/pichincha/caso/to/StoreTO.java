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
public class StoreTO {
	@NotEmpty(message = "id is required")
	@NotNull(message = "id is required")
	private Long id;

	@NotEmpty(message = "name is required")
	@NotNull(message = "name is required")
	private String name;
}