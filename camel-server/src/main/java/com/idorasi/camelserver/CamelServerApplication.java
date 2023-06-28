package com.idorasi.camelserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CamelServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelServerApplication.class, args);
    }

}
