package com.example.demo.web.mode.paymock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankSysResponse {
    //"{\"bankCode\":\"6495\",\"code\":\"0000\",\"reason\":\"[N-0000]处理完成:申请件进件处理成功\",\"sqjNo\":\"" + app_no + "\"}";
    String bankCode;
    String code;
    String reason;
    String sqjNo;
}
