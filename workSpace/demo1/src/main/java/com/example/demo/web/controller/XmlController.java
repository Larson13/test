//package com.example.demo.web.controller;
//
//import com.example.demo.web.mode.*;
//import com.example.demo.web.mode.paymock.XmlPayRequest;
//import com.example.demo.web.utils.wrapper.WrapMapper;
//import com.example.demo.web.utils.wrapper.Wrapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class XmlController {
//
//    private static Logger logger = LoggerFactory.getLogger(XmlController.class);
//
//    @RequestMapping(value = "/analysisXml", method = RequestMethod.POST)
//    public String xmlAnalysis(@RequestBody XmlRequest xmlRequest) {
//
//        logger.info("analysisXml param {}",xmlRequest.toString());
//
//        Map map = new HashMap();
//        map.put("id", xmlRequest.getId());
//        map.put("name", xmlRequest.getName());
//        map.put("shortname", xmlRequest.getShortName());
//        System.out.println(xmlRequest.getShortName());
//        return "dsf";
//    }
//
//    @RequestMapping(value = "/analysisXml2", method = RequestMethod.POST)
//    public Wrapper xmlAnalysis2(@RequestBody XmlPayRequest xmlPayRequest) {
//
//      //  logger.info("analysisXml param {}",xmlPay.toString());
//
//        logger.info("queryId: {}", xmlPayRequest.getRCIQNotifyRequest().get(0).getQueryId());
//
//        Map map = new HashMap();
//        map.put("queryId", xmlPayRequest.getRCIQNotifyRequest().get(0).getQueryId());
//
//        return WrapMapper.ok(map);
//    }
//
//    @PostMapping(value = "/test", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
//    @ResponseBody
//    public TicketResponse test(@RequestBody TicketRequest ticketRequest){
//        logger.info("analysisXml param {}",ticketRequest.toString());
//        TicketResponse ticketResponse=new TicketResponse();
//        List<OrderResponse> orders=new ArrayList<OrderResponse>();
//        OrderResponse o=new OrderResponse();
//        o.setMsg("投注成功");
//        orders.add(o);
//        ticketResponse.setOrderList(orders);
//        return ticketResponse;
//    }
//
//}
//
