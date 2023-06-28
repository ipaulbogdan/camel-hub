package com.idorasi.restproducer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProducerController {


    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world from rest producer!";
    }

}
