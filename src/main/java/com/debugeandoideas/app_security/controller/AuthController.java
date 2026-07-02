package com.debugeandoideas.app_security.controller;

import com.debugeandoideas.app_security.entities.JWTRequest;
import com.debugeandoideas.app_security.entities.JWTResponse;
import com.debugeandoideas.app_security.services.JwtService;
import com.debugeandoideas.app_security.services.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private  final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest jwtRequest){
            this.authenticate(jwtRequest);
            final var userDetails = this.jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
            final String token = this.jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest jwtRequest){
        try{
            this.authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch (BadCredentialsException |DisabledException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
