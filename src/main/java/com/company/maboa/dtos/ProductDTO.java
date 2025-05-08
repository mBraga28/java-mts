package com.company.maboa.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.company.maboa.entities.Category;
import com.company.maboa.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductDTO {
	
	private Long id;
	
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nameProduct;
	
	@Positive(message = "Preço deve ser um valor positivo")
	private Double price;
	
	@NotBlank(message = "Campo requerido")
	private String priceDescription;
	private String image;
	private Integer stock;
	
	@NotBlank(message = "Campo requerido")
	@Size(min = 20, max = 1000, message= "Deve ter entre 20 a 1000 caracteres")
	private String description;
	
	@PastOrPresent(message = "A data do produto não pode ser futura")
	private Instant date;
	
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO() {
	}

	public ProductDTO(Long id, String nameProduct, Double price, String priceDescription, String image, Integer stock, Instant date, String description) {
		this.id = id;
		this.nameProduct = nameProduct;
		this.price = price;
		this.priceDescription = priceDescription;
		this.image = image;
		this.stock = stock;
		this.date = date;
		this.description = description;
	}
	
	public ProductDTO(Product entity) {
		id  = entity.getId();
		nameProduct = entity.getNameProduct();
		price = entity.getPrice();
		priceDescription = entity.getPriceDescription();
		image = entity.getImage();
		stock = entity.getStock();
		date = entity.getDate();
		description = entity.getDescription();
		for (Category cat : entity.getCategories()) {
        	categories.add(new CategoryDTO(cat));
        }
	}
		
	// public ProductDTO(Product entity, Set<Category> categories) {
	// 	this(entity);
	// 	categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
