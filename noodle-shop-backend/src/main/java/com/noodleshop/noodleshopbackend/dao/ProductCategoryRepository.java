package com.noodleshop.noodleshopbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.noodleshop.noodleshopbackend.entity.ProductCategory;

// Name of JSON entry: collectionResourceRel = "productCategory"
// /product-category: path="product-category"

@RepositoryRestResource(collectionResourceRel = "productCategory", path="product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
