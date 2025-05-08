package com.company.maboa.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.maboa.dtos.CategoryDTO;
import com.company.maboa.dtos.ProductDTO;
import com.company.maboa.dtos.ProductDescDTO;
import com.company.maboa.entities.Category;
import com.company.maboa.entities.Product;
import com.company.maboa.repositories.CategoryRepository;
import com.company.maboa.repositories.ProductRepository;
import com.company.maboa.services.IProductService;
import com.company.maboa.services.exceptions.DatabaseException;
import com.company.maboa.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService implements IProductService {
	
	private final ProductRepository productRepository;
	
	private final CategoryRepository categoryRepository;
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional
	public ProductDTO createProduct(ProductDTO productDto) {
		Product entity = new Product();
		copyDtoToEntity(productDto, entity);
		entity.setDate(Instant.now());
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = productRepository.findAll();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product obj = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));;
		return new ProductDTO(obj);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findByCategoryId(Long id) {
		try {
			Category cat = categoryRepository.getReferenceById(id);
			List<Product> list = productRepository.findByCategories(cat);
			return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findByCategoriesName(String name) {
		List<Product> list = productRepository.findByCategoriesName(name);
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductDTO updateProduct(Long id, ProductDTO productDto) {
		try {
			Product entity = productRepository.getReferenceById(id);
			copyDtoToEntity(productDto, entity);
			entity.setDate(Instant.now());
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Override
	public ProductDescDTO updateDescProduct(Long id, ProductDescDTO productDescDto) {
		try {
			Product entity = productRepository.getReferenceById(id);
			copyDescDtoToEntity(productDescDto, entity);
			entity = productRepository.save(entity);
			return new ProductDescDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Override
	public void removeProduct(Long id) {
		try {
			productRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(ProductDTO productDto, Product entity) {
		entity.setNameProduct(productDto.getNameProduct());
		entity.setPrice(productDto.getPrice());
		entity.setPriceDescription(productDto.getPriceDescription());
		entity.setImage(productDto.getImage());
		entity.setStock(productDto.getStock());
		entity.setDate(productDto.getDate());
		entity.setDescription(productDto.getDescription());
		
		entity.getCategories().clear();
		
		for (CategoryDTO catDto : productDto.getCategories()) {
			Category category = categoryRepository.getReferenceById(catDto.getId());
			entity.getCategories().add(category);
		}
	}
	
	private void copyDescDtoToEntity(ProductDescDTO productDescDto, Product entity) {
		entity.setDescription(productDescDto.getDescription());
	}
}
