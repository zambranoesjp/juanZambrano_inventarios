package com.pichincha.caso.to;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import com.pichincha.caso.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTO {

	@NotEmpty(message = "id is required")
	@NotNull(message = "id is required")
	private Long id;

	@NotEmpty(message = "cod is required")
	@NotNull(message = "cod is required")
	private String cod;

	@NotEmpty(message = "name is required")
	@NotNull(message = "name is required")
	private String name;

	@NotEmpty(message = "price is required")
	@NotNull(message = "price is required")
	private Double price;

	@NotEmpty(message = "stock is required")
	@NotNull(message = "stock is required")
	@Min(value = 1, message = "stock cannot be less or equals than zero")
	private Integer stock;

	@Transient
	public Product getProducto() {
		return Product.builder().idProduct(getId()).code(getCod()).name(getName()).price(getPrice()).stock(getStock())
				.build();
	}
}