package com.idorasi.camelserver.processor;

import com.idorasi.camelserver.config.SoapClient;
import com.idorasi.camelserver.config.SoapConfig;
import com.idorasi.camelserver.dto.SoapHelloWorldBody;
import com.idorasi.camelserver.generated.ObjectFactory;
import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SoapComponentProcessor implements Processor {

    private SoapClient soapClient;

    @Override
    public void process(Exchange exchange) throws Exception {
        var objectFactory = new ObjectFactory();
        var body = (SoapHelloWorldBody) exchange.getIn().getBody();

        var request = objectFactory.createHelloWorldRequest();
        request.setName(body.getName());

        var response = soapClient.getBank(request);

        exchange.getIn().setBody(response.getResponse());
    }
}
