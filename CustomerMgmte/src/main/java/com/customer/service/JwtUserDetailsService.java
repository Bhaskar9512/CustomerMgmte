package com.customer.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	 @Autowired
     private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("user not found"));
       // return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
      
        return customer;
        
        
        
       /* ("user".equals(username)) {
              return org.springframework.security.core.userdetails.User.builder()
             .username("user")
             .password("poss")
             .roles("USER")                                     
             .build();
            } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
         }*/
    }
}
