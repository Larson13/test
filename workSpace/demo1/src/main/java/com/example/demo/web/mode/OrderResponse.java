package com.example.demo.web.mode;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class OrderResponse {

    @JacksonXmlProperty(localName = "orderid")
    public String orderId;
    @JacksonXmlProperty(localName = "sysid")
    public String sysId;
    @JacksonXmlProperty(localName = "errorCode")
    public String errorCode;
    @JacksonXmlProperty(localName = "msg")
    public String msg;

}
