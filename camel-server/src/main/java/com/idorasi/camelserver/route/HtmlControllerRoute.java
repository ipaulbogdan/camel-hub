package com.idorasi.camelserver.route;


import com.idorasi.camelserver.config.CamelRestConfig;
import com.idorasi.camelserver.processor.HtmlProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HtmlControllerRoute extends RouteBuilder {


    private CamelRestConfig camelRestConfig;
    private HtmlProcessor htmlProcessor;

    @Override
    public void configure() {
        restConfiguration()
                .port(camelRestConfig.getPort())
                .apiComponent("http-netty")
                .bindingMode(RestBindingMode.json);

        rest("/login")
                .get()
                .produces("text/html;charset=UTF-8")
                .consumes("application/json")
                .to("direct:login");

        rest("/hello")
                .get()
                .param().name("username").type(RestParamType.query)
                .required(true)
                .endParam()
                .produces("text/html;charset=UTF-8")
                .to("direct:hello");

        from("direct:login")
                .log("Received GET request at /login")
                .process(htmlProcessor)
                .log("Html received");

        from("direct:hello")
                .log("Received login request with username:" + header("username"))
                .process(htmlProcessor)
                .log("Html received");

    }
}
