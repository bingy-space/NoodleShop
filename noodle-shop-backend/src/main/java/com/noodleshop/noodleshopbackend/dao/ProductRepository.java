package com.noodleshop.noodleshopbackend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.noodleshop.noodleshopbackend.entity.Product;

// Accept calls from web browser scripts from this origin
// Orgin is: protocol + hostname + port
//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	// Spring Data REST automatically expose endpoint
	// 		http://localhost:8080/api/products/search/findByCategoryId?id={number}
	// Behind the scenes, Spring will execute a query similar to this: 
	// 		SELECT * FROM product WHERE category_id=?
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
	
	// Spring Data REST automatically expose endpoint
	// 		http://localhost:8080/api/products/search/findByNameContaining?name={string}
	// Behind the scenes, Spring will execute a query similar to this: 
	// 		SELECT *
	// 		FROM Product P
	// 		WHERE p.name LIKE CONCAT('%', :name, '%')
	Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

}
