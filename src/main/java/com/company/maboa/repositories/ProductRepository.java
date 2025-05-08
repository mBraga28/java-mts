package com.company.maboa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.maboa.entities.Product;
import com.company.maboa.entities.Category;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategories(Category categories);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.nameCategory = :name")
    List<Product> findByCategoriesName(@Param("name") String name);

}
