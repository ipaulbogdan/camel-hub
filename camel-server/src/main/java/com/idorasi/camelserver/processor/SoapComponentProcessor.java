package com.idorasi.camelserver.processor;

import com.idorasi.camelserver.config.SoapClient;
import com.idorasi.camelserver.config.SoapConfig;
import com.idorasi.camelserver.dto.ApiTech;
import com.idorasi.camelserver.dto.SoapHelloWorldBody;
import com.idorasi.camelserver.generated.HelloWorldResponse;
import com.idorasi.camelserver.generated.ObjectFactory;
import com.idorasi.camelserver.util.UrlFormatter;
import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SoapComponentProcessor implements Processor {

    private SoapClient soapClient;
    private SoapConfig soapConfig;
    private UrlFormatter urlFormatter;

    @Override
    public void process(Exchange exchange) {
        var objectFactory = new ObjectFactory();
        var body = (SoapHelloWorldBody) exchange.getIn().getBody();

        var request = objectFactory.createHelloWorldRequest();
        request.setName(body.getName());

        var response = soapClient.postHello(request);

        exchange.getIn().setBody(formatUrl(response));
    }

    private HelloWorldResponse formatUrl(HelloWorldResponse helloWorldResponse) {
        if (helloWorldResponse.getRedirectUrl() != null) {
            helloWorldResponse.setRedirectUrl(urlFormatter.formatUrl(helloWorldResponse.getRedirectUrl(),
                    soapConfig.getBaseUri(),
                    ApiTech.SOAP));
        }

        return helloWorldResponse;
    }
}
