package com.pichincha.caso.to;

import java.util.List;

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
public class OrderTo {
	@NotNull
	private Long idClient;

	@NotNull
	@NotEmpty
	private List<OrderDetailTO> details;
}