package com.thecoder777.k8sreadyspringboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/world")
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }
}
