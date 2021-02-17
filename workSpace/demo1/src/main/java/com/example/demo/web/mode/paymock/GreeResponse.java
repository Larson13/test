package com.example.demo.web.mode.paymock;

import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class GreeResponse {

       String msg;
       String code;
        List<BankSysResponse> list;
}
