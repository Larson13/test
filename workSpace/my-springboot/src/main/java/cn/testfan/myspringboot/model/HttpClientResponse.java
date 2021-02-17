package cn.testfan.myspringboot.model;

import lombok.*;

import java.util.Map;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpClientResponse  {

	//响应状态码
	private int code;

	// 响应头map
	private Map<String, String> headers;

	// 响应体数据
	private String content;

}
