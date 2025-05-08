package com.company.maboa.dtos;

//import java.util.ArrayList;
//import java.util.List;

import com.company.maboa.entities.Category;

public class CategoryDTO {
	
	private Long id;
	private String nameCategory;
	private String iconClass;
	
	//private List<ProductDTO> products = new ArrayList<>();
	
	
	public CategoryDTO() {
	}

    public CategoryDTO(Long id, String nameCategory, String iconClass) {
		this.id = id;
		this.nameCategory = nameCategory;
		this.iconClass = iconClass;
	}

	public CategoryDTO(Category entity) {
		this.id  = entity.getId();
		this.nameCategory = entity.getNameCategory();
		this.iconClass = entity.getIconClass();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	//public List<ProductDTO> getProducts() {
	//	return products;
	//}

}
