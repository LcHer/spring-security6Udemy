package com.debugeandoideas.app_security.security;

import com.debugeandoideas.app_security.repositories.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerUserDetails implements UserDetailsService {

    public CustomerUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  this.customerRepository.findByEmail(username)
                .map(
                        customer -> {
                    var authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
                    return new User(customer.getEmail(),customer.getPassword(),authorities);
                }
        ).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
