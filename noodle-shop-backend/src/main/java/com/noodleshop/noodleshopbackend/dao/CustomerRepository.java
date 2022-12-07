package com.noodleshop.noodleshopbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noodleshop.noodleshopbackend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	// Behind the scenes, Spring will execute a query similar to this:
	// SELECT * FROM Customer c WHERE c.email = theEmail
	// Method returns null if not found
	Customer findByEmail(String theEmail);
}