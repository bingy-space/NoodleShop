package com.noodleshop.noodleshopbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noodleshop.noodleshopbackend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
