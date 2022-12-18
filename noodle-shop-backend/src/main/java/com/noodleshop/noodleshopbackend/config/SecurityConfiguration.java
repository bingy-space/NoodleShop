package com.noodleshop.noodleshopbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// Protect endpoint /api/orders
		// Only accessible to authenticated users
		http.authorizeRequests(configurer -> configurer.antMatchers("/api/orders/**").authenticated()).oauth2ResourceServer().jwt();
		
		// Add CORS filters
		http.cors();
		
		return http.build();
	}
}
