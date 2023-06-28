package com.idorasi.camelserver.config;

import javax.xml.bind.JAXBElement;
import com.idorasi.camelserver.generated.HelloWorldResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapClient extends WebServiceGatewaySupport {

    public HelloWorldResponse getBank(Object request){
        Object res = getWebServiceTemplate().marshalSendAndReceive(request);
        return (HelloWorldResponse) res;
    }
}

