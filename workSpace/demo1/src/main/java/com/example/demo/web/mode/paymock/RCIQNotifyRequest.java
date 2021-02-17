package com.example.demo.web.mode.paymock;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;


@Data
public class RCIQNotifyRequest {

    @JacksonXmlProperty(localName = "version")
    String version;


    @JacksonXmlProperty(localName = "instId")
    Integer instId;

    @JacksonXmlProperty(localName = "bankId")
    Integer bankId;


    @JacksonXmlProperty(localName = "secBankId")
    Integer secBankId;


    @JacksonXmlProperty(localName = "certId")
    Integer certId;

    @JacksonXmlProperty(localName = "queryId")
    String queryId;

    @JacksonXmlProperty(localName = "applyId")
    String applyId;

    @JacksonXmlProperty(localName = "chnnelNum")
    String chnnelNum;

    @JacksonXmlProperty(localName = "appType")
    String appType;

    @JacksonXmlProperty(localName = "applyDate")
    String applyDate;

    @JacksonXmlProperty(localName = "cardProductId")
    String cardProductId;

    @JacksonXmlProperty(localName = "isAttachCard")
    String isAttachCard;

    @JacksonXmlProperty(localName = "certType")
    String certType;

    @JacksonXmlProperty(localName = "name")
    String name;

    @JacksonXmlProperty(localName = "pname1")
    String pname1;

    @JacksonXmlProperty(localName = "pname2")
    String pname2;

    @JacksonXmlProperty(localName = "gender")
    String gender;

    @JacksonXmlProperty(localName = "nationality")
    String nationality;

    @JacksonXmlProperty(localName = "national")
    String national;

    @JacksonXmlProperty(localName = "certNo")
    String certNo;

    @JacksonXmlProperty(localName = "birthday")
    String birthday;

    @JacksonXmlProperty(localName = "validDate")
    String validDate;

    @JacksonXmlProperty(localName = "mobilePhone")
    String mobilePhone;

    @JacksonXmlProperty(localName = "maritalStatus")
    String maritalStatus;


    @JacksonXmlProperty(localName = "educationDegree")
    String educationDegree;

    @JacksonXmlProperty(localName = "homeAddress1")
    String homeAddress1;

    @JacksonXmlProperty(localName = "homeAddress2")
    String homeAddress2;

    @JacksonXmlProperty(localName = "homeAddress3")
    String homeAddress3;

    @JacksonXmlProperty(localName = "homeAddressPost")
    String homeAddressPost;

    @JacksonXmlProperty(localName = "email")
    String email;

    @JacksonXmlProperty(localName = "cardSpellCode")
    String cardSpellCode;

    @JacksonXmlProperty(localName = "companyName")
    String companyName;



    @JacksonXmlProperty(localName = "companyAddress1")
    String companyAddress1;

    @JacksonXmlProperty(localName = "companyAddress2")
    String companyAddress2;

    @JacksonXmlProperty(localName = "companyAddress3")
    String companyAddress3;

    @JacksonXmlProperty(localName = "companyAddress4")
    String companyAddress4;

    @JacksonXmlProperty(localName = "companyPost")
    String companyPost;

    @JacksonXmlProperty(localName = "companyPhone")
    String companyPhone;

    @JacksonXmlProperty(localName = "homeIncome")
    String homeIncome;

    @JacksonXmlProperty(localName = "companyPhoneExt")
    String companyPhoneExt;

    @JacksonXmlProperty(localName = "kinfolkName")
    String kinfolkName;

    @JacksonXmlProperty(localName = "kinfolkRelation")
    String kinfolkRelation;

    @JacksonXmlProperty(localName = "kinfolkPhone")
    String kinfolkPhone;

    @JacksonXmlProperty(localName = "bnkSprNo")
    String bnkSprNo;

    @JacksonXmlProperty(localName = "billType")
    String billType;

    @JacksonXmlProperty(localName = "billAddress")
    String billAddress;

    @JacksonXmlProperty(localName = "postAddress")
    String postAddress;

    @JacksonXmlProperty(localName = "cardMoney")
    String cardMoney;

    @JacksonXmlProperty(localName = "postType")
    String postType;
}

