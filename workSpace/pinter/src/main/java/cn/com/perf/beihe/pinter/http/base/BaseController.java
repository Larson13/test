package cn.com.perf.beihe.pinter.http.base;

import cn.com.perf.beihe.pinter.mode.RestResponse;

public class BaseController {
    private static final String OK = "0";

    private static final String KO = "1";

    private static final String OK_MESSAGE = "success";

    protected RestResponse<Object> wrap(Object obj) {
        RestResponse<Object> response = new RestResponse();
        response.setCode("0");
        response.setMessage("success");
        response.setData(obj);
        return response;
    }

    protected RestResponse<Object> wrap() {
        RestResponse<Object> response = new RestResponse();
        response.setCode("0");
        response.setMessage("success");
        return response;
    }

    protected RestResponse<Object> wrap(String code, String message) {
        RestResponse<Object> response = new RestResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    protected RestResponse<Object> wrap(String code, String message, Object data) {
        RestResponse<Object> response = new RestResponse();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
