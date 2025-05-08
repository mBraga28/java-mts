package com.company.maboa.services.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.maboa.entities.Category;
import com.company.maboa.entities.Product;
import com.company.maboa.repositories.CategoryRepository;
import com.company.maboa.repositories.ProductRepository;

@Service
public class DBService {
	
	private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

	public DBService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
	}
	
    @Transactional
	public void instanceDB() {
		Product p1 = productRepository.findById(1L).get();
        Product p2 = productRepository.findById(2L).get();
        Product p3 = productRepository.findById(3L).get();
        Product p4 = productRepository.findById(4L).get();
        Product p5 = productRepository.findById(5L).get();     
        Product p6 = productRepository.findById(6L).get();
        Product p7 = productRepository.findById(7L).get();
        Product p8 = productRepository.findById(8L).get();     
        Product p9 = productRepository.findById(9L).get();     
        Product p10 = productRepository.findById(10L).get();     
        Product p11 = productRepository.findById(11L).get();     
        Product p12 = productRepository.findById(12L).get();     
        Product p13 = productRepository.findById(13L).get();     
        Product p14 = productRepository.findById(14L).get();     
        Product p15 = productRepository.findById(15L).get();     
        Product p16 = productRepository.findById(16L).get();     
        Product p17 = productRepository.findById(17L).get();     
        Product p18 = productRepository.findById(18L).get();     
        Product p19 = productRepository.findById(19L).get();     
        Product p20 = productRepository.findById(20L).get();     
        Product p21 = productRepository.findById(21L).get();     
        Product p22 = productRepository.findById(22L).get();     
        Product p23 = productRepository.findById(23L).get();     
        Product p24 = productRepository.findById(24L).get();     
        Product p25 = productRepository.findById(25L).get();     
        Product p26 = productRepository.findById(26L).get();     
        Product p27 = productRepository.findById(27L).get();     
        Product p28 = productRepository.findById(28L).get();
		
        // Category cat1 = categoryRepository.findById(1L).get();
        // Category cat3 = categoryRepository.findById(3L).get();
        // Category cat5 = categoryRepository.findById(5L).get();
        // Category cat6 = categoryRepository.findById(6L).get();

        // cat1.getProducts().addAll(Arrays.asList(p2, p25, p26));
        // cat3.getProducts().addAll(Arrays.asList(p10, p11, p16, p27, p28));
        // cat5.getProducts().addAll(Arrays.asList(p4, p5, p6, p17, p18, p24));
        // cat6.getProducts().addAll(Arrays.asList(p1, p3, p7, p8, p9, p12, p13, p14, p15, p19, p20, p21, p22, p23));

        // p1.getCategories().addAll(Arrays.asList(cat6));
        // p2.getCategories().addAll(Arrays.asList(cat1));
        // p3.getCategories().addAll(Arrays.asList(cat6));
        // p4.getCategories().addAll(Arrays.asList(cat5));
        // p5.getCategories().addAll(Arrays.asList(cat5));
        // p6.getCategories().addAll(Arrays.asList(cat5));
        // p7.getCategories().addAll(Arrays.asList(cat6));
        // p8.getCategories().addAll(Arrays.asList(cat6));
        // p9.getCategories().addAll(Arrays.asList(cat6));
        // p10.getCategories().addAll(Arrays.asList(cat3));
        // p11.getCategories().addAll(Arrays.asList(cat3));
        // p12.getCategories().addAll(Arrays.asList(cat6));
        // p13.getCategories().addAll(Arrays.asList(cat6));
        // p14.getCategories().addAll(Arrays.asList(cat6));
        // p15.getCategories().addAll(Arrays.asList(cat6));
        // p16.getCategories().addAll(Arrays.asList(cat3));
        // p17.getCategories().addAll(Arrays.asList(cat5));
        // p18.getCategories().addAll(Arrays.asList(cat5));
        // p19.getCategories().addAll(Arrays.asList(cat6));
        // p20.getCategories().addAll(Arrays.asList(cat6));
        // p21.getCategories().addAll(Arrays.asList(cat6));
        // p22.getCategories().addAll(Arrays.asList(cat6));
        // p23.getCategories().addAll(Arrays.asList(cat6));
        // p24.getCategories().addAll(Arrays.asList(cat6));    
        // p25.getCategories().addAll(Arrays.asList(cat1));    
        // p26.getCategories().addAll(Arrays.asList(cat1));    
        // p27.getCategories().addAll(Arrays.asList(cat3));    
        // p28.getCategories().addAll(Arrays.asList(cat3));

        // p1.setDescriptionOne("Precise and ergonomic, this gaming mouse is built for fast-paced gameplay.\nEnhance your performance with smooth tracking and responsive controls.");
        // p2.setDescriptionTwo("Ideal for professionals and gamers who demand stunning picture quality.");
        // p3.setDescriptionOne("");
        // p4.setDescriptionOne("");
        // p5.setDescriptionOne("");
        // p6.setDescriptionOne("");
        // p7.setDescriptionOne("");
        // p8.setDescriptionOne("");
        // p9.setDescriptionOne("");
        // p10.setDescriptionOne("");
        // p11.setDescriptionOne("");    
        // p12.setDescriptionOne("");    
        // p13.setDescriptionOne("");    
        // p14.setDescriptionOne("");    
        // p15.setDescriptionOne("");    
        // p16.setDescriptionOne("");    
        // p17.setDescriptionOne("");    
        // p18.setDescriptionOne("");    
        // p19.setDescriptionOne("");    
        // p20.setDescriptionOne("");    
        // p21.setDescriptionOne("");    
        // p22.setDescriptionOne("");    
        // p23.setDescriptionOne("");    
        // p24.setDescriptionOne("");    
        // p25.setDescriptionOne("");    
        // p26.setDescriptionOne("");    
        // p27.setDescriptionOne("");    
        // p28.setDescriptionOne("");

        //Adiciona descriptionTwo ao final de descriptionOne, separados por \n
        // for (Product p : Arrays.asList(p1, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28)) {
        //     String desc = p.getDescription() != null ? p.getDescription().trim() : "";
        //     String descOne = p.getDescriptionOne() != null ? p.getDescriptionOne().trim() : "";
        //     String descTwo = p.getDescriptionTwo() != null ? p.getDescriptionTwo().trim() : "";
        //     if (!descTwo.isEmpty()) {
        //         if (!descOne.isEmpty()) {
        //             desc = descOne + "\n\n" + descTwo;
        //         } else {
        //             desc = descTwo; 
        //         }
        //         p.setDescription(desc);
        //     }
        // }

        // productRepository.saveAll(Arrays.asList(p1, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28));

        // productRepository.save(p2);

        // categoryRepository.saveAll(Arrays.asList(cat1, cat3, cat5, cat6));
        // productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28));
		// Optional<Product> prodOptional = productRepository.findById(1L);
		// if (prodOptional.isPresent()) {
		// 	Product existingProduct = prodOptional.get();
		// 	existingProduct.setDescriptionOne(existingProduct.getDescriptionOne());
		// 	existingProduct.setDescriptionTwo(existingProduct.getDescriptionTwo());
		// 	productRepository.save(existingProduct);
		// }
	}
}
