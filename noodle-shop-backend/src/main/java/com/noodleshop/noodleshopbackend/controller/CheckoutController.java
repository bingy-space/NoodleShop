package com.noodleshop.noodleshopbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noodleshop.noodleshopbackend.dto.PaymentInfo;
import com.noodleshop.noodleshopbackend.dto.Purchase;
import com.noodleshop.noodleshopbackend.dto.PurchaseResponse;
import com.noodleshop.noodleshopbackend.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	private CheckoutService checkoutService;
	
	@Autowired
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		
		return purchaseResponse;
	}
	
	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException{
		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
		String paymentString = paymentIntent.toJson();
		
		return new ResponseEntity<>(paymentString, HttpStatus.OK);
	}
}
