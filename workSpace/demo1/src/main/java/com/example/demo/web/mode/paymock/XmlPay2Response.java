package com.example.demo.web.mode.paymock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class XmlPay2Response {
    String bankCode;
    String code;
    String reason;
    String sqjNo;
}
