package com.noodleshop.noodleshopbackend.dto;

import java.util.Set;

import com.noodleshop.noodleshopbackend.entity.Address;
import com.noodleshop.noodleshopbackend.entity.Customer;
import com.noodleshop.noodleshopbackend.entity.Order;
import com.noodleshop.noodleshopbackend.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order oredr;
	private Set<OrderItem> orderItemrs;
}
