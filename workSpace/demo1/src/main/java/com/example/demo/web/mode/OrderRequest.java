package com.example.demo.web.mode;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class OrderRequest {
    @JacksonXmlProperty(localName = "lotterytype")
    public String lotteryType;
    @JacksonXmlProperty(localName = "phase")
    public String phase;
    @JacksonXmlProperty(localName = "orderid")
    public String orderid;

}
