package com.idorasi.camelserver.util;

import com.idorasi.camelserver.config.CamelRestConfig;
import com.idorasi.camelserver.dto.ApiTech;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
public class UrlFormatter {

    private static final String redirectParam = "redirectUrl";

    private CamelRestConfig camelRestConfig;

    public String formatUrl(String url, String producerBaseUrl, ApiTech apiTech) {
        var redirectUrlPath = UriComponentsBuilder.fromHttpUrl(url).build().getPath();
        var producerRedirectUrl = UriComponentsBuilder.fromHttpUrl(producerBaseUrl)
                .path(redirectUrlPath)
                .build().toString();

        return UriComponentsBuilder.fromHttpUrl(camelRestConfig.getBaseUrl())
                .path(getRedirectCamelRoute(apiTech))
                .queryParam(redirectParam, producerRedirectUrl)
                .build().toString();
    }

    private String getRedirectCamelRoute(ApiTech apiTech) {
        return switch (apiTech) {
            case REST -> camelRestConfig.getRestHelloPath();
            case SOAP -> camelRestConfig.getSoapHelloPath();
        };
    }

}
