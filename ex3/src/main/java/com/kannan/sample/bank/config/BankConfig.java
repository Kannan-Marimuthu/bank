package com.kannan.sample.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BankConfig {
	@Bean
	RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
}
