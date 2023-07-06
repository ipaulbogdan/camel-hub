package com.idorasi.camelserver.config;

import com.idorasi.camelserver.generated.HelloWorldResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapClient extends WebServiceGatewaySupport {

    public HelloWorldResponse postHello(Object request){
        Object res = getWebServiceTemplate().marshalSendAndReceive(request);
        return (HelloWorldResponse) res;
    }
}

