package com.noodleshop.noodleshopbackend.service;

import com.noodleshop.noodleshopbackend.dto.PaymentInfo;
import com.noodleshop.noodleshopbackend.dto.Purchase;
import com.noodleshop.noodleshopbackend.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
	PurchaseResponse placeOrder(Purchase purchase);
	
	PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
