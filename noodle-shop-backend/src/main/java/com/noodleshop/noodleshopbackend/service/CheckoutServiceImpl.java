package com.noodleshop.noodleshopbackend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.noodleshop.noodleshopbackend.dao.CustomerRepository;
import com.noodleshop.noodleshopbackend.dto.Purchase;
import com.noodleshop.noodleshopbackend.dto.PurchaseResponse;

public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;

	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public PurchaseResponse placeOrder(Purchase purchase) {
		// TODO Auto-generated method stub
		return null;
	}

}
