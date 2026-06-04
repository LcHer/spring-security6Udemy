package com.debugeandoideas.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/loans","/balance","/accounts","/cards").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

   /* @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager (){
        var admin = User.withUsername("Admin")
                .password("to_be_encoded")
                .authorities("ADMIN")
                .build();
        var user = User.withUsername("user")
                .password("to_be_encoded")
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }*/

   /* @Bean //esta configuracion de JPA usa la base de datos de users en la bd que se tiene como default de spring
    UserDetailsService userDetailsService (DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    PasswordEncoder bCryptPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
