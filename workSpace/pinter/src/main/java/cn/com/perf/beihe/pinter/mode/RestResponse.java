package cn.com.perf.beihe.pinter.mode;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
    public class RestResponse<T> implements Serializable {
        private static final long serialVersionUID = 1L;

        private String code;

        private String message;

        private T data;
    }





