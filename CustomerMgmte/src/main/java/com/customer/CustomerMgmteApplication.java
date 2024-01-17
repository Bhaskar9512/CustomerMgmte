package com.customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.customer.util")
public class CustomerMgmteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMgmteApplication.class, args);
	}

}
