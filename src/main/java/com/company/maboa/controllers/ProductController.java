package com.company.maboa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.maboa.services.IProductService;

import com.company.maboa.dtos.ProductDTO;
import com.company.maboa.dtos.ProductDescDTO;
import com.company.maboa.services.IFileStorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    private final IFileStorageService fileStorageService;

    public ProductController(IProductService productService, IFileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(consumes = { "multipart/form-data"})
    public ResponseEntity<ProductDTO> createProduct(
            @RequestPart("product") @Valid ProductDTO productDto,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile)

    /// No front-end, seu formulário deve ter um <input type="file"
    /// name="imageFile">
    /// e pode enviar os dados do produto em um campo ou em formato JSON para o
    /// campo "product".
    /// Essa abordagem permite separar o upload do arquivo e o processamento dos
    /// dados do produto.
    {

        // Salva o arquivo e obtém o caminho/URL
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = fileStorageService.storeFile(imageFile);
            productDto.setImage(imagePath);
        }

        ProductDTO product = productService.createProduct(productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestPart("product") @Valid ProductDTO productDto,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
    
        // Salva o arquivo e obtém o caminho/URL, se houver nova imagem
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = fileStorageService.storeFile(imageFile);
            productDto.setImage(imagePath);
        }
    
        ProductDTO product = productService.updateProduct(id, productDto);
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProductDescDTO> updateDescProduct(@PathVariable Long id,
            @Valid @RequestBody ProductDescDTO productDescDto) {
        ProductDescDTO product = productService.updateDescProduct(id, productDescDto);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }

}
