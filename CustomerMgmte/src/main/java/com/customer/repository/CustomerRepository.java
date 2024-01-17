package com.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.customer.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	boolean existsById(Long id);
	public Optional<Customer> findByEmail(String email);

	//Customer findByEmail(String email);
	

}
