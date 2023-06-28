package com.idorasi.camelserver.route;

import com.idorasi.camelserver.dto.SoapHelloWorldBody;
import com.idorasi.camelserver.processor.RestComponentProcessor;
import com.idorasi.camelserver.processor.SoapComponentProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RestRoute extends RouteBuilder {

    private RestComponentProcessor restComponentProcessor;
    private SoapComponentProcessor soapComponentProcessor;

    @Override
    public void configure() {
        restConfiguration().host("localhost")
                .port(9090)
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


        from("direct:hello-world-rest")
                .log("Received GET request at /rest/hello-world")
                .process(restComponentProcessor)
                .log("Received body from rest producer: " + body());

        from("direct:hello-world-soap")
                .log("Received GET request at /soap/hello-world")
                .process(soapComponentProcessor)
                .log("Received body from soap producer: " + body());
    }
}
