package com.idorasi.camelserver.route;

import com.idorasi.camelserver.config.CamelRestConfig;
import com.idorasi.camelserver.dto.SoapHelloWorldBody;
import com.idorasi.camelserver.processor.RestComponentProcessor;
import com.idorasi.camelserver.processor.SoapComponentProcessor;
import com.idorasi.camelserver.processor.SoapRedirectProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RestControllerRoute extends RouteBuilder {

    private CamelRestConfig camelRestConfig;
    private RestComponentProcessor restComponentProcessor;
    private SoapComponentProcessor soapComponentProcessor;
    private SoapRedirectProcessor soapRedirectProcessor;

    @Override
    public void configure() {
        restConfiguration()
                .port(camelRestConfig.getPort())
                .apiComponent("http-netty")
                .bindingMode(RestBindingMode.json);

        rest("/rest")
                .get("/hello-world")
                .produces("application/json")
                .consumes("application/json")
                .to("direct:hello-world-rest");

        rest("/soap")
                .post("/hello-world")
                .type(SoapHelloWorldBody.class)
                .produces("application/json")
                .consumes("application/json")
                .to("direct:hello-world-soap");

        rest("/rest")
                .get("/redirect")
                .param()
                .name("redirectUrl").type(RestParamType.query)
                .endParam()
                .produces("application/json")
                .consumes("application/json")
                .to("direct:rest-redirect");

        rest("/soap")
                .get("/redirect")
                .produces("application/json")
                .consumes("application/json")
                .to("direct:soap-redirect");

        from("direct:hello-world-rest")
                .log("Received GET request at /rest/hello-world")
                .process(restComponentProcessor)
                .log("Received body from rest producer: " + body());

        from("direct:rest-redirect")
                .log("Received GET request at /rest/redirect")
                .process(restComponentProcessor)
                .log("Received body from rest producer: " + body());

        from("direct:soap-redirect")
                .log("Received POST request at /soap/redirect")
                .process(soapRedirectProcessor)
                .log("Received body from soap producer: " + body());

        from("direct:hello-world-soap")
                .log("Received POST request at /soap/hello-world")
                .process(soapComponentProcessor)
                .log("Received body from soap producer: " + body());
    }
}
