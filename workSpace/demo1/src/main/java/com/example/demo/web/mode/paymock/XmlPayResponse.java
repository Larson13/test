package com.example.demo.web.mode.paymock;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@JacksonXmlRootElement(localName = "Cartoon")
@Data
@Builder
public class XmlPayResponse {

    @JacksonXmlElementWrapper(localName ="Message ")
    @JacksonXmlProperty(localName = "NotifyAccept ")
    private RCIQNotifyResponse RCIQNotifyRequest;
}