package com.noodleshop.noodleshopbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noodleshop.noodleshopbackend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
