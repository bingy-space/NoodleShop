package com.noodleshop.noodleshopbackend.dao;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.noodleshop.noodleshopbackend.entity.Order;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long>{
	// Spring will execute a query similar to this:
	// SELECT * FROM orders
	// LEFT OUTER JOIN customer
	// ON orders.customer_id = customer.id
	// WHERE customer.email = :email
	Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);
}
