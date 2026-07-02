package com.debugeandoideas.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,JWTValidationFilter jwtValidationFilter) throws Exception {
        http.sessionManagement(
                sess->sess.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS));

        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");


        http.authorizeHttpRequests(auth ->
                //auth.requestMatchers("/loans","/balance","/accounts","/cards")
                        auth
                                /* ESTA SECCION ES PARA TRABAJAR CON AUTORITIES
                                .requestMatchers("/loans").hasAuthority("VIEW_LOANS")
                                .requestMatchers("/balance").hasAuthority("VIEW_BALANCE")
                                .requestMatchers("/cards").hasAuthority("VIEW_CARDS")
                                //.requestMatchers("/accounts").hasAnyAuthority("VIEW_ACCOUNT","VIEW_CARDS")
                                .anyRequest().permitAll())*/
                .requestMatchers("/loans","/balance").hasRole("USER")
                .requestMatchers("/accounts","/cards").hasRole("ADMIN")
                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        http.addFilterAfter(jwtValidationFilter,BasicAuthenticationFilter.class);
        http.cors(cors-> corsConfigurationSource());
        http.csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers("/welcome","about_us","/authenticate")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
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
    public PasswordEncoder passwordEncoder (){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }

@Bean
    CorsConfigurationSource corsConfigurationSource (){
        var conf = new CorsConfiguration();
       //conf.setAllowedOrigins(List.of("http://localhost:4200/","http://my-app.com"));
        conf.setAllowedOrigins(List.of("*"));
        //(conf.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        conf.setAllowedMethods(List.of("*"));
        conf.setAllowedHeaders(List.of("*"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",conf);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    }
