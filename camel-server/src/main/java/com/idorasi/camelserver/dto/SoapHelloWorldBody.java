package com.idorasi.camelserver.dto;

import java.net.URL;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SoapHelloWorldBody {

    private String name;
    private String redirectUrl;

}
