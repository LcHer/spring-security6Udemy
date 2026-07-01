package com.debugeandoideas.app_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path="/accounts")
public class AccountController {
    //@PreAuthorize("hasAnyAuthority('VIEW_ACCOUNT','VIEW_CARDS')") //anotacionp para hacer algo anters de autorizar se recomienda usdar anotaciones si nuestro proyecto tiene mas de 30 endpoints
    @GetMapping
    public Map<String, String> accounts(){
        //logica de negocio
        return Collections.singletonMap("msj","accounts");
    }
}
