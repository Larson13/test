package com.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class httptest {

    public static void main(String[] args)  {
//        String url="http://localhost:8087/bankSys";
//
//// 创建Httpclient对象
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//// 参数
//        String payload = "<Cartoon>\n" +
//                "    <Message id=\"YL202210201804004056883714\">\n" +
//                "        <RCIQNotify id=\"RCIQNotify\">\n" +
//                "            <version>1.0.1</version>\n" +
//                "            <instId>10005</instId>\n" +
//                "            <bankId>6495</bankId>\n" +
//                "            <secBankId>9999</secBankId>\n" +
//                "            <certId>10005</certId>\n" +
//                "            <queryId>2210200052868840001</queryId>\n" +
//                "            <applyId>Jccaapply9980439745065000003</applyId>\n" +
//                "            <chnnelNum>JD</chnnelNum>\n" +
//                "            <appType>1</appType>\n" +
//                "            <applyDate>20221020180400</applyDate>\n" +
//                "            <cardProductId>0003</cardProductId>\n" +
//                "            <isAttachCard>2</isAttachCard>\n" +
//                "            <certType>01</certType>\n" +
//                "            <name>戴莫</name>\n" +
//                "            <pname1>LI</pname1>\n" +
//                "            <pname2>YAHU</pname2>\n" +
//                "            <gender>M</gender>\n" +
//                "            <nationality>1</nationality>\n" +
//                "            <national>001</national>\n" +
//                "            <certNo>110101199001010007</certNo>\n" +
//                "            <birthday>19960307</birthday>\n" +
//                "            <validDate>20420307</validDate>\n" +
//                "            <mobilePhone>15232111111</mobilePhone>\n" +
//                "            <maritalStatus>B</maritalStatus>\n" +
//                "            <educationDegree>1</educationDegree>\n" +
//                "            <homeAddress1>广西省南宁市</homeAddress1>\n" +
//                "            <homeAddress2>兴宁区</homeAddress2>\n" +
//                "            <homeAddress3>大苏打</homeAddress3>\n" +
//                "            <homeAddressPost>530000</homeAddressPost>\n" +
//                "            <email>11111@qq.com</email>\n" +
//                "            <cardSpellCode>LI/YAHU/</cardSpellCode>\n" +
//                "            <companyName>一二三四五六七八九十</companyName>\n" +
//                "            <companyAddress1>广西省南宁市</companyAddress1>\n" +
//                "            <companyAddress2>兴宁区</companyAddress2>\n" +
//                "            <companyAddress3>一二三四五六七八九十一二三四五</companyAddress3>\n" +
//                "            <companyAddress4>六七八九十一二三四五六七八九十</companyAddress4>\n" +
//                "            <companyPost>530000</companyPost>\n" +
//                "            <homeIncome>11</homeIncome>\n" +
//                "            <companyPhone>012-21321321</companyPhone>\n" +
//                "            <companyPhoneExt>1231</companyPhoneExt>\n" +
//                "            <kinfolkName>打赏</kinfolkName>\n" +
//                "            <kinfolkRelation>D</kinfolkRelation>\n" +
//                "            <kinfolkPhone>15454545454</kinfolkPhone>\n" +
//                "            <bnkSprNo>111111</bnkSprNo>\n" +
//                "            <billType>EM</billType>\n" +
//                "            <billAddress>B</billAddress>\n" +
//                "            <postAddress>B</postAddress>\n" +
//                "            <cardMoney>F</cardMoney>\n" +
//                "            <postType>COUR</postType>\n" +
//                "        </RCIQNotify>\n" +
//                "        <ds:Signature\n" +
//                "            xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
//                "            <ds:SignedInfo>\n" +
//                "                <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n" +
//                "                <ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n" +
//                "                <ds:Reference URI=\"#RCIQNotify\">\n" +
//                "                    <ds:Transforms>\n" +
//                "                        <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>\n" +
//                "                    </ds:Transforms>\n" +
//                "                    <ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
//                "                    <ds:DigestValue>PUak21xV4hhF6n3724YCMs/NfbM=</ds:DigestValue>\n" +
//                "                </ds:Reference>\n" +
//                "            </ds:SignedInfo>\n" +
//                "            <ds:SignatureValue>\n" +
//                "VoL7dGSL24962diQLG8FTHglpq8V66Y68vJTjiVpZTlnAK2sSIEDbUzmz2omJKlVHm5nxr4gRkjR\n" +
//                "TK5diwVudL37Hg8Xf7csoUoUMGTCJRMLT4tK9ScSYR07c+qpkUszwLPn1ROYH4JhWLLEnpss/C3+\n" +
//                "VhoyNExLi7JXt8lXvOA=\n" +
//                "</ds:SignatureValue>\n" +
//                "        </ds:Signature>\n" +
//                "    </Message>\n" +
//                "</Cartoon>\n";
//// 定义请求的参数
//        URI uri = new URIBuilder()
//                .setScheme("http")
//                .setHost("localhost")
//                .setPath("/bankSys")
//                .setPort(8087) // int类型端口
//                .build();
//// 创建http请求
//        HttpPost request = new HttpPost(uri);
//        request.setHeader("Content-Type", "application/xml");
//
//        request.setEntity(new StringEntity(StringEscapeUtils.unescapeJava(payload)));
//        System.out.println(uri.toString());
////response 对象
//        CloseableHttpResponse response = null;
//
//        response = httpclient.execute(request);
//// 判断返回状态是否为200
//        if (response.getStatusLine().getStatusCode() == 200) {
//            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
//            System.out.println(content);
//        }

        String host = "localhost";
        String url = "/bankSys";
        int port = 8087;
        String Content_Type = "application/xml";
        String params = "<Cartoon>\n" +
                "    <Message id=\"YL202210201804004056883714\">\n" +
                "        <RCIQNotify id=\"RCIQNotify\">\n" +
                "            <version>1.0.1</version>\n" +
                "            <instId>10005</instId>\n" +
                "            <bankId>6495</bankId>\n" +
                "            <secBankId>9999</secBankId>\n" +
                "            <certId>10005</certId>\n" +
                "            <queryId>2210200052868840001</queryId>\n" +
                "            <applyId>Jccaapply9980439745065000003</applyId>\n" +
                "            <chnnelNum>JD</chnnelNum>\n" +
                "            <appType>1</appType>\n" +
                "            <applyDate>20221020180400</applyDate>\n" +
                "            <cardProductId>0003</cardProductId>\n" +
                "            <isAttachCard>2</isAttachCard>\n" +
                "            <certType>01</certType>\n" +
                "            <name>戴莫</name>\n" +
                "            <pname1>LI</pname1>\n" +
                "            <pname2>YAHU</pname2>\n" +
                "            <gender>M</gender>\n" +
                "            <nationality>1</nationality>\n" +
                "            <national>001</national>\n" +
                "            <certNo>110101199001010007</certNo>\n" +
                "            <birthday>19960307</birthday>\n" +
                "            <validDate>20420307</validDate>\n" +
                "            <mobilePhone>15232111111</mobilePhone>\n" +
                "            <maritalStatus>B</maritalStatus>\n" +
                "            <educationDegree>1</educationDegree>\n" +
                "            <homeAddress1>广西省南宁市</homeAddress1>\n" +
                "            <homeAddress2>兴宁区</homeAddress2>\n" +
                "            <homeAddress3>大苏打</homeAddress3>\n" +
                "            <homeAddressPost>530000</homeAddressPost>\n" +
                "            <email>11111@qq.com</email>\n" +
                "            <cardSpellCode>LI/YAHU/</cardSpellCode>\n" +
                "            <companyName>一二三四五六七八九十</companyName>\n" +
                "            <companyAddress1>广西省南宁市</companyAddress1>\n" +
                "            <companyAddress2>兴宁区</companyAddress2>\n" +
                "            <companyAddress3>一二三四五六七八九十一二三四五</companyAddress3>\n" +
                "            <companyAddress4>六七八九十一二三四五六七八九十</companyAddress4>\n" +
                "            <companyPost>530000</companyPost>\n" +
                "            <homeIncome>11</homeIncome>\n" +
                "            <companyPhone>012-21321321</companyPhone>\n" +
                "            <companyPhoneExt>1231</companyPhoneExt>\n" +
                "            <kinfolkName>打赏</kinfolkName>\n" +
                "            <kinfolkRelation>D</kinfolkRelation>\n" +
                "            <kinfolkPhone>15454545454</kinfolkPhone>\n" +
                "            <bnkSprNo>111111</bnkSprNo>\n" +
                "            <billType>EM</billType>\n" +
                "            <billAddress>B</billAddress>\n" +
                "            <postAddress>B</postAddress>\n" +
                "            <cardMoney>F</cardMoney>\n" +
                "            <postType>COUR</postType>\n" +
                "        </RCIQNotify>\n" +
                "        <ds:Signature\n" +
                "            xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
                "            <ds:SignedInfo>\n" +
                "                <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n" +
                "                <ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n" +
                "                <ds:Reference URI=\"#RCIQNotify\">\n" +
                "                    <ds:Transforms>\n" +
                "                        <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>\n" +
                "                    </ds:Transforms>\n" +
                "                    <ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
                "                    <ds:DigestValue>PUak21xV4hhF6n3724YCMs/NfbM=</ds:DigestValue>\n" +
                "                </ds:Reference>\n" +
                "            </ds:SignedInfo>\n" +
                "            <ds:SignatureValue>\n" +
                "VoL7dGSL24962diQLG8FTHglpq8V66Y68vJTjiVpZTlnAK2sSIEDbUzmz2omJKlVHm5nxr4gRkjR\n" +
                "TK5diwVudL37Hg8Xf7csoUoUMGTCJRMLT4tK9ScSYR07c+qpkUszwLPn1ROYH4JhWLLEnpss/C3+\n" +
                "VhoyNExLi7JXt8lXvOA=\n" +
                "</ds:SignatureValue>\n" +
                "        </ds:Signature>\n" +
                "    </Message>\n" +
                "</Cartoon>\n";

        String content = HttpClientUtil.doPost(host,url,port,params);
        System.out.println(content);
    }
}
