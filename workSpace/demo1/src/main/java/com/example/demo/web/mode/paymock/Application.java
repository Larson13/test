package com.example.demo.web.mode.paymock;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Application {
    @JacksonXmlProperty(localName = "APP_NO")
    private String APP_NO;

}
