package com.test;

import lombok.*;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Description: httpClient工具类
 */
public class HttpClientUtils {

    // 编码格式。发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";

    // 设置连接超时时间，单位毫秒。
    private static final int CONNECT_TIMEOUT = 30000;

    // 请求获取数据的超时时间(即响应时间)，单位毫秒。
    private static final int SOCKET_TIMEOUT = 30000;


    /**
     * 发送get请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static HttpClientResponse doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    /**
     * 发送get请求；带请求参数
     *
     * @param url    请求地址
     * @param params 请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResponse doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, null, params);
    }

    /**
     * 发送get请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResponse doGet(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }

        // 创建http对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        /**
         * setConnectTimeout：设置连接超时时间，单位毫秒。
         * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
         * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
         * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
         */
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpGet.setConfig(requestConfig);

        // 设置请求头
        packageHeader(headers, httpGet);

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = null;

        try {
            // 执行请求并获得响应结果
            return getHttpClientResult(httpClient, httpGet);
        } finally {
            // 释放资源
            release(httpClient);
        }
    }

    /**
     * 发送post请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static HttpClientResponse doPost(String url) throws Exception {
        return doPost(url, null, null);
    }

    /**
     * 发送post请求；带请求参数
     *
     * @param url    请求地址
     * @param params 参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResponse doPost(String url, Map<String, String> params) {
        return doPost(url, null, params);
    }

    /**
     * 发送post请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResponse doPost(String url, Map<String, String> headers, Map<String, String> params) {

        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建http对象
        HttpPost httpPost = new HttpPost(url);
        /**
         * setConnectTimeout：设置连接超时时间，单位毫秒。
         * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
         * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
         * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
         */
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPost.setConfig(requestConfig);
        packageHeader(headers, httpPost);
        HttpClientResponse result = null;
        try {
            if (params != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }

                // 设置到请求的http对象中
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
            }
            // 执行请求并获得响应结果
            result = getHttpClientResult(httpClient, httpPost);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            release(httpClient);
        }
        
        return result;
    }


    public static HttpClientResponse doPostJson(String url, String json) {
        return doPostJson(url, json, null);
    }


    public static HttpClientResponse doPostJson(String url, String json, Map<String, String> headers) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String resultString = "";
        // 创建Http Post请求
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPost.setConfig(requestConfig);
        // 创建请求内容
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        packageHeader(headers, httpPost);
        HttpClientResponse result = null;
        try {
            // 执行http请求
            result = getHttpClientResult(httpClient, httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(httpClient);
        }
        return result;
    }


    public static HttpClientResponse doPostFile(String url, Map<String, String> fileParams, Map<String, String> formParams) throws ParseException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientResponse result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(CharsetUtils.get("UTF-8"));
            if (fileParams != null) {
                for (Entry<String, String> entry : fileParams.entrySet()) {
                    builder.addBinaryBody(entry.getKey(), new File(entry.getValue()));
                }
            }

            if (formParams != null) {
                for (Entry<String, String> entry : formParams.entrySet()) {
                    builder.addTextBody(entry.getKey(), entry.getValue());
                }
            }
            // 以浏览器兼容模式运行，防止文件名乱码。
            HttpEntity reqEntity = builder.build();
            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            result = getHttpClientResult(httpClient, httpPost);

        } finally {
            release(httpClient);
        }
        return result;
    }

    public static HttpClientResponse doPostFile(String url, Map<String, String> fileParams) throws IOException {
        return doPostFile(url, fileParams, null);
    }

    /**
     * Description: 封装请求头
     *
     * @param params
     * @param httpMethod
     */
    public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        // 封装请求头
        if (params != null) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                // 设置到请求头到HttpRequestBase对象中
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * Description: 获得响应结果
     *
     * @param httpClient
     * @param httpMethod
     * @return
     * @throws Exception
     */
    private static HttpClientResponse getHttpClientResult(CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws IOException {
        // 执行请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        // 获取返回结果
        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            Map<String, String> headers = buildHeaderMap(httpResponse.getAllHeaders());
            String content = "";
            if (httpResponse.getEntity() != null) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return HttpClientResponse.builder().code(httpResponse.getStatusLine().getStatusCode()).content(content).headers(headers).build();
        }
        return HttpClientResponse.builder().code(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
    }

    private static Map<String, String> buildHeaderMap(Header[] allHeaders) {
        Map<String, String> headerMap = new HashMap<>();
        if (allHeaders != null) {
            for (Header header : allHeaders) {
                headerMap.put(header.getName(), header.getValue());
            }
        }
        return headerMap;
    }

    /**
     * Description: 释放资源
     *
     * @param httpClient
     * @throws IOException
     */
    private static void release(CloseableHttpClient httpClient) {
        try {
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userName", "admin");
        map.put("password", "1234");
        HttpClientUtils.doPost("http://localhost:8080/pinter/bank/api/login", map);
    }
}