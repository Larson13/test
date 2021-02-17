package com.example.demo.web.mode.paymock;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;
@JacksonXmlRootElement(localName = "TaskPack")
@Data
public class XmlPay2Request {

    @JacksonXmlElementWrapper(localName ="Applications")
    @JacksonXmlProperty(localName ="Application")
    private List<Application> Application;
}
