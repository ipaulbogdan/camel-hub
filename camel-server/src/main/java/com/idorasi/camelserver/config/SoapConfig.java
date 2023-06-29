package com.idorasi.camelserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@ConfigurationProperties(prefix = "soap-producer")
@Setter
@Getter
public class SoapConfig {

    private String baseUri;

    @Bean
    public Jaxb2Marshaller marshaller()  {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.idorasi.camelserver.generated");
        return marshaller;
    }
    @Bean
    public SoapClient soapConnector(Jaxb2Marshaller marshaller) {
        SoapClient client = new SoapClient();
        client.setDefaultUri(baseUri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
