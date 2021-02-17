package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装的响应实体类
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemoResponse {

    private Integer code;

    private String msg;

    private Object data;
}
