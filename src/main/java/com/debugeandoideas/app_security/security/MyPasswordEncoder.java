package com.debugeandoideas.app_security.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
public class MyPasswordEncoder {//implements PasswordEncoder {

    //@Override
    public String encode(CharSequence rawPassword) {
        //en esta parte se puede enviar a otro sistema a encriptar depende de la logica de cada negocio
        return String.valueOf(rawPassword.toString().hashCode());
    }

    //@Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        var passwordAsString=String.valueOf(rawPassword.toString().hashCode());
        return encodedPassword.equals(passwordAsString);
    }
}
