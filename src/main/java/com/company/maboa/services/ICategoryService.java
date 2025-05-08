package com.company.maboa.services;

import java.util.List;

import com.company.maboa.dtos.CategoryDTO;


public interface ICategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDto);
	List<CategoryDTO> findAll();
	CategoryDTO findById(Long id);
	CategoryDTO findByName(String nameCategory);
	CategoryDTO updateCategory(Long id, CategoryDTO categoryDto);
	void removeCategory(Long id);


}
