package com.noodleshop.noodleshopbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// Protect endpoint /api/orders
		// Only accessible to authenticated users
		http.authorizeRequests(configurer -> configurer.antMatchers("/api/orders/**").authenticated()).oauth2ResourceServer().jwt();
		
		// Add CORS filters
		http.cors();
		
		// Add content negotiation strategy
		// Set up content negotiation strategy to support Okta sending back friendly response
		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
		
		// Force a non-empty response body for 401's to make the response more friendly
		Okta.configureResourceServer401ResponseBody(http);
		
		// Disable CSRF since we are not using Cookies for session tracking
		http.csrf().disable();
		
		return http.build();
	}
}
