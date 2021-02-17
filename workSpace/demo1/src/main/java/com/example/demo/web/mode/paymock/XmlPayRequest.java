package com.example.demo.web.mode.paymock;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@JacksonXmlRootElement(localName = "Cartoon")
@Data
public class XmlPayRequest {


    @JacksonXmlElementWrapper(localName ="Message")
    @JacksonXmlProperty(localName ="RCIQNotify")
    private List<RCIQNotifyRequest> RCIQNotifyRequest;


}
