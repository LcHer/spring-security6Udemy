package com.debugeandoideas.app_security.security;

import com.debugeandoideas.app_security.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       final var username = authentication.getName();
       final var pwd = authentication.getCredentials().toString();
       final var customerFromDb = this.customerRepository.findByEmail(username);

       final var customer=customerFromDb.orElseThrow(()->new BadCredentialsException("Invalid credentials"));
       final var customerPwd = customer.getPassword();
       final var customerPwdRaw = customerPwd.startsWith("{") ? customerPwd : "{noop}" + customerPwd; //este prefijo solo se usa para que no se ocupe el bycrypt del password ya que Spring solo lo requiere encriptado

       if (passwordEncoder.matches(pwd,customerPwdRaw)){
           final var roles = customer.getRoles();
           final var authorities = roles.stream()
                   .map(role-> new SimpleGrantedAuthority(role.getName()))
                   .collect(Collectors.toList());
           return new UsernamePasswordAuthenticationToken(username,pwd,authorities);
       }else{
           throw new BadCredentialsException("Invalid credentials");

       }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
