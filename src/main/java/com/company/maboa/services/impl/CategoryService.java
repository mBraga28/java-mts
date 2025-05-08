package com.company.maboa.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.maboa.dtos.CategoryDTO;
import com.company.maboa.entities.Category;
import com.company.maboa.repositories.CategoryRepository;
import com.company.maboa.services.ICategoryService;
import com.company.maboa.services.exceptions.DatabaseException;
import com.company.maboa.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService implements ICategoryService {	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional
	public CategoryDTO createCategory(CategoryDTO categoryDto) {
		Category entity = new Category();
		entity.setNameCategory(categoryDto.getNameCategory());
		entity.setIconClass(categoryDto.getIconClass());
		entity = categoryRepository.save(entity);
		return new CategoryDTO(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = categoryRepository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CategoryDTO findByName(String name) {
		Optional<Category> obj = categoryRepository.findByNameCategory(name);
		return new CategoryDTO(obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
	}

	@Override
	@Transactional
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDto) {
		try {
			Category entity = categoryRepository.getReferenceById(id);
			entity.setNameCategory(categoryDto.getNameCategory());
			entity.setIconClass(categoryDto.getIconClass());
			entity = categoryRepository.save(entity);
			return new CategoryDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Override
	public void removeCategory(Long id) {
		try {
			categoryRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
