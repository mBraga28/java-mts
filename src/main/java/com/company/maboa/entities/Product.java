package com.company.maboa.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameProduct;
	private Double price;
	private String priceDescription;
	
	@Column(columnDefinition = "TEXT")
	private String image;
	private Integer stock;
	
	@JoinColumn(name = "description")
	@Schema(description = "Descrição principal do produto", 
            example = "Este produto possui as seguintes características:\n\n" +
                     "- Característica 1\n" +
                     "- Característica 2\n" +
                     "- Característica 3")
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;
	
	@ManyToMany
	@JoinTable(name = "tb_product_category", 
			   joinColumns = @JoinColumn(name = "product_id"),
			   inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories = new HashSet<>();
	
	public Product() {
	}

	public Product(Long id, String nameProduct, Double price, String priceDescription,
			String image, Integer stock, String description) {
		super();
		this.id = id;
		this.nameProduct = nameProduct;
		this.price = price;
		this.priceDescription = priceDescription;
		this.image = image;
		this.stock = stock;
		this.description = description;
	}
	
	public Product(Long id, String nameProduct, Double price, String priceDescription,
			String image, Integer stock, String description, Instant date) {
		super();
		this.id = id;
		this.nameProduct = nameProduct;
		this.price = price;
		this.priceDescription = priceDescription;
		this.image = image;
		this.stock = stock;
		this.description = description;
		this.date = date;
	}
	
	public Product(Long id, String description) {
		this.id = id;
		this.description = description;
	}

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

	public Set<Category> getCategories() {
		return categories;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameProduct == null) ? 0 : nameProduct.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nameProduct == null) {
			if (other.nameProduct != null)
				return false;
		} else if (!nameProduct.equals(other.nameProduct))
			return false;
		return true;
	}
	
	
}
