package com.example.enviodetareasservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/envio")
    public ResponseEntity<String> homeController(){
        return new ResponseEntity<>("bienvenido al servicio de envío", HttpStatus.OK);
    }
}
