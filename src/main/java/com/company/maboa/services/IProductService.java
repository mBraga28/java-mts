package com.company.maboa.services;

import java.util.List;

import com.company.maboa.dtos.ProductDTO;
import com.company.maboa.dtos.ProductDescDTO;

public interface IProductService {
	
	ProductDTO createProduct(ProductDTO productDto);
	List<ProductDTO> findAll();
	ProductDTO findById(Long id);
	List<ProductDTO> findByCategoryId(Long id);
	List<ProductDTO> findByCategoriesName(String name);
	ProductDTO updateProduct(Long id, ProductDTO productDto);
	ProductDescDTO updateDescProduct(Long id, ProductDescDTO productDescDto);
	void removeProduct(Long id);

}
