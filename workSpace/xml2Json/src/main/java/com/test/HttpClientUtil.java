package com.test;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientUtil {
    public static String doPost(String host, String url, int port, String params) {
            //创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
// 定义请求的参数
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath(url)
                    .setPort(port) // int类型端口
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
// 创建http请求
        HttpPost request = new HttpPost(uri);
        request.setHeader("Content-Type", "application/xml");

        try {
            request.setEntity(new StringEntity(StringEscapeUtils.unescapeJava(params)));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(uri.toString());
//response 对象
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String content = null;
// 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(content);
        }
        return content;
    }




    }
