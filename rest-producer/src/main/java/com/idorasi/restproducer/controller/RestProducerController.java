package com.idorasi.restproducer.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Setter
public class RestProducerController {

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/hello")
    public Body helloWorld() {
        return new Body()
                .setMessage("First hello!")
                .setRedirectUrl("http://localhost:" + serverPort + "/redirect/hello");
    }

    @GetMapping("/redirect/hello")
    public Body redirectHello() {
        return new Body().setMessage("Second hello");
    }

    @Data
    @Accessors(chain = true, fluent = false)
    public static class Body {
        private String message;
        private String redirectUrl;
    }

}
