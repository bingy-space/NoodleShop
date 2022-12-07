package com.noodleshop.noodleshopbackend.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noodleshop.noodleshopbackend.dao.CustomerRepository;
import com.noodleshop.noodleshopbackend.dto.Purchase;
import com.noodleshop.noodleshopbackend.dto.PurchaseResponse;
import com.noodleshop.noodleshopbackend.entity.Customer;
import com.noodleshop.noodleshopbackend.entity.Order;
import com.noodleshop.noodleshopbackend.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;

	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		// Retrieve the order info from dto
		Order order = purchase.getOrder();		
				
		// Generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// Populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		// Populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());		
		order.setShippingAddress(purchase.getShippingAddress());
		
		// Populate customer with order
		Customer customer = purchase.getCustomer();
		
		// Check if this is an existing customer
		String theEmailString = customer.getEmail();
		Customer customerFromDBCustomer = customerRepository.findByEmail(theEmailString);
		
		if(customerFromDBCustomer != null) {
			// Found them, assign them accordingly
			customer = customerFromDBCustomer;
		}
		
		
		customer.add(order);
		
		// Save to the database
		customerRepository.save(customer);
		
		// Return a response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		// Generate a random UUID number (UUID version-4)
		// For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier	
		
		return UUID.randomUUID().toString();
	}

}
