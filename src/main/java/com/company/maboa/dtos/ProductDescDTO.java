package com.company.maboa.dtos;

import com.company.maboa.entities.Product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;


public class ProductDescDTO {
	
	private Long id;
	
	@NotBlank(message = "Campo requerido")
	@Column(columnDefinition = "TEXT")
	private String description;
	
	public ProductDescDTO() {
	}

	public ProductDescDTO(Long id, String description) {
		this.id = id;
		
		this.description = description;
	}
	
	public ProductDescDTO(Product entity) {
		id  = entity.getId();
		description = entity.getDescription();
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
