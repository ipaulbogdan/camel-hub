package com.idorasi.soapproducer.endpoint;

import com.idorasi.hello.HelloWorldRequest;
import com.idorasi.hello.HelloWorldResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class WebServiceEndpoint {

    private static final String NAMESPACE_URI = "http://idorasi.com/hello";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HelloWorldRequest")
    @ResponsePayload
    public HelloWorldResponse hello(@RequestPayload HelloWorldRequest helloWorldRequest) {
        log.info("Received request with param: " + helloWorldRequest.getName());

        var response = new HelloWorldResponse();
        response.setMessage("Hello " + helloWorldRequest.getName());
        response.setRedirectUrl("http://localhost:9092/ws");

        return response;
    }
}
