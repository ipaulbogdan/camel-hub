package com.idorasi.camelserver.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SoapHelloWorldBody {

    private String name;

}
