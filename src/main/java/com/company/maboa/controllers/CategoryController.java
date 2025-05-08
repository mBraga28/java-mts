package com.company.maboa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.maboa.dtos.CategoryDTO;
// import com.company.maboa.dtos.ProductDTO;
import com.company.maboa.services.ICategoryService;
// import com.company.maboa.services.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	private final ICategoryService categoryService;

    // private final IProductService productService;

    public CategoryController(ICategoryService categoryService/*, IProductService productService*/) {
        this.categoryService = categoryService;
        // this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto) {
        CategoryDTO category = categoryService.createCategory(categoryDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }  

    // @GetMapping(value = "/{id}")
    // public ResponseEntity<List<ProductDTO>> findProductsByCategory(@PathVariable Long id) {
    //     List<ProductDTO> list = productService.findByCategoryId(id);
    //     return ResponseEntity.ok().body(list);
    // }

    // @GetMapping(value = "{id}/name/{name}")
    // public ResponseEntity<List<ProductDTO>> findProductsByCategoryName(@PathVariable Long id, @PathVariable String name) {
    // 	CategoryDTO category = categoryService.findByName(id, name);
    //     List<ProductDTO> list = productService.findByCategoriesName(name);
    //     return ResponseEntity.ok().body(list);
    // }
    @GetMapping(value = "name/{name}")
    public ResponseEntity<CategoryDTO> findProductsByCategoryName(@PathVariable String name) {
    	CategoryDTO category = categoryService.findByName(name);
        return ResponseEntity.ok().body(category);
    }
    
    @PutMapping(value= "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDto) {
        CategoryDTO category = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok().body(category);
    }
    
    @DeleteMapping(value= "/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable Long id) {
        categoryService.removeCategory(id);
        return ResponseEntity.noContent().build();
    }    

}
