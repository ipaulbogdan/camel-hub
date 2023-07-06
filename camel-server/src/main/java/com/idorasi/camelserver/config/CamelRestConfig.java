package com.idorasi.camelserver.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "camel.netty")
public class CamelRestConfig {

    private String url;
    private String port;
    private String restHelloPath;
    private String soapHelloPath;

    public String getBaseUrl() {
        return url + ":" + port;
    }
}
