//package com.example.demo.web.controller;
//
//import com.example.demo.web.mode.paymock.*;
//import com.example.demo.web.utils.HttpClientUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Slf4j
//public class bankSysMockController {
//
//
////    @PostMapping(value = "/bankSys", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
////    public Wrapper bankSys(@RequestBody XmlPayRequest xmlPayRequest) {
////
////        //  logger.info("analysisXml param {}",xmlPay.toString());
////
////        log.info("queryId: {}", xmlPayRequest.getRCIQNotifyRequest().get(0).getQueryId());
////
////        Map map = new HashMap();
////        map.put("queryId", xmlPayRequest.getRCIQNotifyRequest().get(0).getQueryId());
////
////        return WrapMapper.ok(map);
////    }
//
//    @PostMapping(value = "/bankSys", consumes = {MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_XML_VALUE)
//    public String bankSys1(@RequestBody XmlPayRequest xmlPayRequest) {
//
//        //  log.info("请求报文： param {}",xmlPay.toString());
//        String queryId = xmlPayRequest.getRCIQNotifyRequest().get(0).getQueryId();
//        log.info("解析到的queryId: {}", queryId);
//        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
//                "<Cartoon>\n" +
//                "<Message id=\"YL202210201804004056883714\">\n" +
//                "<NotifyAccept id=\"NotifyAccept\">\n" +
//                "<version>1.01</version>\n" +
//                "<instId>20001</instId>\n" +
//                "<bankId></bankId>\n" +
//                "<secBankId></secBankId>\n" +
//                "<certId>0300120180913001</certId>\n" +
//                "<timesStamp>1666260343817</timesStamp>\n" +
//                "</NotifyAccept>\n" +
//                "<ds:Signature\n" +
//                "xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
//                "<ds:SignedInfo>\n" +
//                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n" +
//                "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n" +
//                "<ds:Reference URI=\"#NotifyAccept\">\n" +
//                "<ds:Transforms>\n" +
//                "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>\n" +
//                "</ds:Transforms>\n" +
//                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
//                "<ds:DigestValue>v0a1tBaeRNMIuPi7zOYdLo+5b0c=</ds:DigestValue>\n" +
//                "</ds:Reference>\n" +
//                "</ds:SignedInfo>\n" +
//                "<ds:SignatureValue>\n" +
//                "eTNMMVbQpoPDV0WwUnbz92sXpoJ5tulWM9btXsg0oCcqQhLrwM9g8sJt96Kr/cKVCI8SwANjcvtiT+gW1nJ0AEvRDYzdLwQbsVqJ48yQJT70K/Z3gMkpkOq71a8ws4hDs0vopW/W8uTzAajkd6/WijZRqaDw27N3es+CYTDoSU4=\n" +
//                "</ds:SignatureValue>\n" +
//                "</ds:Signature>\n" +
//                "</Message>\n" +
//                "</Cartoon>\n";
//        RCIQNotifyResponse rr = new RCIQNotifyResponse();
//        rr.setStr(str);
//        XmlPayResponse rsp = XmlPayResponse.builder().RCIQNotifyRequest(rr).build();
//        // log.info("rsp: {}",rsp);
//        String host = "10.192.247.1";
//        String url = "/riskControlInfoNotify";
//        int port = 4001;
//        String Content_Type = "application/xml";
//        String params = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Cartoon><Message id=\"47754843645418735585\"><RCINotify id=\"RCINotify\"><version>1.01</version><instId>20001</instId><bankId>6495</bankId><secBankId>9999</secBankId><certId>0300120180913001</certId><queryId>" + queryId + "</queryId><ckUserWithMallMobilePhone></ckUserWithMallMobilePhone><ckUserWithVerifiedName></ckUserWithVerifiedName><ckUserWithVerifiedMobilePhone></ckUserWithVerifiedMobilePhone><ckUserWithVerifiedCertNo></ckUserWithVerifiedCertNo><commonAddressStability></commonAddressStability><commonAddressClassification></commonAddressClassification><commonAddressProvince></commonAddressProvince><commonAddressCity></commonAddressCity><onlineShoppingAgingLevelPrediction></onlineShoppingAgingLevelPrediction><onlineShoppingFrequency></onlineShoppingFrequency><carIndexClassification></carIndexClassification><homeIndexClassification></homeIndexClassification><haveChildClassification></haveChildClassification><ckPlus></ckPlus><ckVerified></ckVerified><workForecast></workForecast><incomeForecast></incomeForecast><bindSavingCardNum></bindSavingCardNum><bindCreditCardNum></bindCreditCardNum><memberTime></memberTime><jdMemberLevel></jdMemberLevel><monthSelfOperatedOrder></monthSelfOperatedOrder><monthSelfOperated1Order></monthSelfOperated1Order><threeMonthAgoSelfOperated1Order></threeMonthAgoSelfOperated1Order><threeMonthAgoSelfOperated2Order></threeMonthAgoSelfOperated2Order><sixMonthAgoSelfOperated2Order></sixMonthAgoSelfOperated2Order><twelveMonthAgoSelfOperated2Order></twelveMonthAgoSelfOperated2Order><monthSelfOperated3Order></monthSelfOperated3Order><threeMonthAgoSelfOperated3Order></threeMonthAgoSelfOperated3Order><sixMonthAgoSelfOperated3Order></sixMonthAgoSelfOperated3Order><twelveMonthAgoSelfOperated3Order></twelveMonthAgoSelfOperated3Order><monthPayMethod></monthPayMethod><monthPayMethodNumCount></monthPayMethodNumCount><monthPayMethodMoneyCount></monthPayMethodMoneyCount><threeMonthPayMethod></threeMonthPayMethod><threeMonthPayMethodNumCount></threeMonthPayMethodNumCount><sixMonthPayMethod></sixMonthPayMethod><sixMonthPayMethodNumCount></sixMonthPayMethodNumCount><sixMonthPayMethodMoneyCount></sixMonthPayMethodMoneyCount><twelveMonthPayMethod></twelveMonthPayMethod><twelveMonthPayMethodNumCount></twelveMonthPayMethodNumCount><twelveMonthOrderDay></twelveMonthOrderDay><twelveMonthTop1Name></twelveMonthTop1Name><twelveMonthTop1Order></twelveMonthTop1Order><twelveMonthTop1OrderAmount></twelveMonthTop1OrderAmount><monthPayRatio></monthPayRatio><threeMonthPayRatio></threeMonthPayRatio><sixMonthPayRatio></sixMonthPayRatio><twelveMonthPayRatio></twelveMonthPayRatio><threeMonthMallPhysicalOrder></threeMonthMallPhysicalOrder><sixMonthMallPhysicalOrder></sixMonthMallPhysicalOrder><twelveMonthMallPhysicalOrder></twelveMonthMallPhysicalOrder><last30MobileUser></last30MobileUser><last30PCUser></last30PCUser><mallAvgAnnualOrderAmount></mallAvgAnnualOrderAmount><activateWhiteBarTime></activateWhiteBarTime><whiteBarAmount></whiteBarAmount><whiteBarCanUseAmount></whiteBarCanUseAmount><ckOpenSmallVault></ckOpenSmallVault><goldBarActivateUser></goldBarActivateUser><smallVaultCurrentAmount></smallVaultCurrentAmount><creditCardRepayUser></creditCardRepayUser><userWhiteBar1Count></userWhiteBar1Count><userWhiteBar3Count></userWhiteBar3Count><userWhiteBar6Count></userWhiteBar6Count><userWhiteBar12Count></userWhiteBar12Count><whiteBarAvgAmount></whiteBarAvgAmount><maxOverdueDayLevel></maxOverdueDayLevel><billStageForestLevel></billStageForestLevel><whiteBarBillCount></whiteBarBillCount><ckWhiteBarUser></ckWhiteBarUser><ckSmallWhiteCardUserId></ckSmallWhiteCardUserId><passSmallWhiteCardUserId></passSmallWhiteCardUserId><ckRejected></ckRejected><applySmallWhiteCardCount></applySmallWhiteCardCount><haveSmallWhiteCardCount></haveSmallWhiteCardCount><badHabit></badHabit><promotionSensitivity></promotionSensitivity><c3ConsumerPreference></c3ConsumerPreference><motherConsumerPreference></motherConsumerPreference><lifeConsumerPreference></lifeConsumerPreference><clothingConsumerPreference></clothingConsumerPreference><travelConsumerPreference></travelConsumerPreference><carConsumerPreference></carConsumerPreference><petConsumerPreference></petConsumerPreference><userBuyingPower></userBuyingPower><jkScore></jkScore><apsResultAdvice>A</apsResultAdvice><creditRiskRating></creditRiskRating><cheatRiskRating></cheatRiskRating><maxRecommendAmount></maxRecommendAmount><priceLevelAdvice></priceLevelAdvice><ckNeedCare></ckNeedCare><cheatRiskPoint></cheatRiskPoint><creditRiskPoint></creditRiskPoint><industryCategoryUnderCode></industryCategoryUnderCode><companyCheckConsistency></companyCheckConsistency><companyCheckClass></companyCheckClass><homeAddCheckConsistency></homeAddCheckConsistency><homeAddCheckClass></homeAddCheckClass><freeCreditQuota></freeCreditQuota><guestGroupClass></guestGroupClass><mobileStability></mobileStability><mobileBrand></mobileBrand><mobileModel></mobileModel><mobileOs></mobileOs><ckJdWithUnionPay></ckJdWithUnionPay><ckPastYearRepayUser></ckPastYearRepayUser><messageSendTime></messageSendTime><submitTime></submitTime><carrierIdVeriResult></carrierIdVeriResult><faceRecognitionResult></faceRecognitionResult><deliveryAddress12Count></deliveryAddress12Count><riskSignature>042775a48f145c1fb857aae6567b1ecb</riskSignature><companyIndustryCode></companyIndustryCode><custrValNo></custrValNo><unitKind></unitKind><unionCompany></unionCompany><unionReserveField1></unionReserveField1><unionReserveField2></unionReserveField2><unionReserveField3></unionReserveField3><unionReserveField4></unionReserveField4><unionReserveField5></unionReserveField5><unionReserveField6></unionReserveField6><unionReserveField7></unionReserveField7><unionReserveField8></unionReserveField8><unionReserveField9></unionReserveField9><unionReserveField10></unionReserveField10><companyAddressCode1></companyAddressCode1><companyAddressCode2></companyAddressCode2><totAmt></totAmt><jccaAscore></jccaAscore><dtLevel></dtLevel><jtModel></jtModel><denyCode></denyCode><riskPointLv1></riskPointLv1><riskPointLv2></riskPointLv2><riskTag1></riskTag1><riskTag2></riskTag2><riskTag3></riskTag3><riskTag4></riskTag4><riskTag5></riskTag5><riskTag6></riskTag6><riskTag7></riskTag7><riskTag8></riskTag8><riskTag9></riskTag9><riskTag10></riskTag10></RCINotify><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
//                "<ds:SignedInfo>\n" +
//                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n" +
//                "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n" +
//                "<ds:Reference URI=\"#RCINotify\">\n" +
//                "<ds:Transforms>\n" +
//                "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>\n" +
//                "</ds:Transforms>\n" +
//                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
//                "<ds:DigestValue>06ieXL1WjDv3whQxjaL/jPYG3rI=</ds:DigestValue>\n" +
//                "</ds:Reference>\n" +
//                "</ds:SignedInfo>\n" +
//                "<ds:SignatureValue>\n" +
//                "tWFbCdPHWvBAcN3Rtxy1NiIbRtjNb+MUKkQet4wssmvr8l/LkzJHUBHGH9EO/p4o2u0dxcodU7d+\n" +
//                "q7K/uT6FFIWD9sw3FaGn+bpCW2s3WdITDIAM7iQagCwW4AFwwzXgROXoxVjJh4SMjvYewnm2nCbx\n" +
//                "7ZL1CI38GeISUG28hTQ=\n" +
//                "</ds:SignatureValue>\n" +
//                "</ds:Signature></Message></Cartoon>\n";
//
//        String content = HttpClientUtil.doPost(host, url, port, params);
//
//        log.info("mock回调银数系统请求参数:{}", params);
//
//        return str;
//    }
//
//
//    @PostMapping(value = "/bankSys3", consumes = {MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_XML_VALUE)
//    @ResponseBody
//    public String bankSys3(@RequestBody XmlPay2Request xmlPay2Request) {
//        log.info("请求报文： {}", xmlPay2Request.toString());
//        String app_no = xmlPay2Request.getApplication().get(0).getAPP_NO();
//        log.info("解析到的APP_NO: {}", app_no);
//        String rps = "{\"bankCode\":\"6495\",\"code\":\"0000\",\"reason\":\"[N-0000]处理完成:申请件进件处理成功\",\"sqjNo\":\"" + app_no + "\"}";
//
//        return rps;
//    }
//
//
//    @PostMapping(value = "/bankSys2")
//    //@ResponseBody
//    public String bankSys2(@RequestBody String reqXml) {
//        log.info("请求报文： {}", reqXml);
//        String[] arr = reqXml.split("&");
//        //  log.info("arr： {}", arr[1]);
//        String xml = arr[1];
//        int index = xml.indexOf("<APP_NO>");
//        int indexend = xml.indexOf("</APP_NO>");
//        String app_no = xml.substring(index + 8, indexend);
//        log.info("app_no： {}", app_no);
//
//
//        String rps = "bankCode=6495, code=0000, reason=[N-0000]处理完成:申请件进件处理成功, sqjNo="+app_no;
////        BankSysResponse rps = BankSysResponse.builder()
////                .bankCode("6495")
////                .code("0000")
////                .reason("[N-0000]处理完成:申请件进件处理成功")
////                .sqjNo(app_no)
////                .build();
//        log.info("返回报文: {}", rps);
//        return rps.toString();
//    }
//}
