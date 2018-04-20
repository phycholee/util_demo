package com.llf.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Oliver.li
 * @Description: HTTP请求工具类，只针对返回json数据
 * @date: 2018/4/18 10:52
 */
@SuppressWarnings("Duplicates")
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static JSONObject get(String url) throws Exception {
        return get(url, null);
    }

    public static JSONObject get(String url, Map<String, String> params) throws Exception {
        return get(url, params, null);
    }

    public static JSONObject get(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        Assert.state(url != null && !"".equals(url), "url cannot be empty!");

        StringBuilder sb = new StringBuilder(url);

        if (params != null && params.size() > 0) {
            boolean firstTag = true;

            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries){
                if (firstTag){
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstTag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        String result = httpRequest(sb.toString(), HttpMethod.GET, null, headers, false);
        return JSON.parseObject(result, JSONObject.class);
    }

    public static JSONObject post(String url) throws Exception {
        return post(url, null);
    }

    public static JSONObject post(String url, Map<String, String> params) throws Exception {
        return post(url, params, null);
    }

    public static JSONObject post(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        return post(url, params, headers, false);
    }

    public static JSONObject post(String url, Map<String, String> params, Map<String, String> headers, boolean isJsonRequest) throws Exception {
        String result = httpRequest(url, HttpMethod.POST, params, headers, isJsonRequest);
        return JSON.parseObject(result, JSONObject.class);
    }

    public static JSONObject put(String url) throws Exception {
        return put(url, null);
    }

    public static JSONObject put(String url, Map<String, String> params) throws Exception {
        return put(url, params, null);
    }

    public static JSONObject put(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        return put(url, params, headers, false);
    }

    public static JSONObject put(String url, Map<String, String> params, Map<String, String> headers, boolean isJsonRequest) throws Exception {
        String result = httpRequest(url, HttpMethod.PUT, params, headers, isJsonRequest);
        return JSON.parseObject(result, JSONObject.class);
    }

    public static JSONObject delete(String url) throws Exception {
        return delete(url, null);
    }

    public static JSONObject delete(String url, Map<String, String> params) throws Exception {
        return delete(url, params, null);
    }

    public static JSONObject delete(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        Assert.state(url != null && !"".equals(url), "url cannot be empty!");

        StringBuilder sb = new StringBuilder(url);

        if (params != null && params.size() > 0) {
            boolean firstTag = true;

            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries){
                if (firstTag){
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstTag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        String result = httpRequest(sb.toString(), HttpMethod.DELETE, null, headers, false);
        return JSON.parseObject(result, JSONObject.class);
    }

    private static String httpRequest(String url, HttpMethod method, Map<String, String> params, Map<String, String> headers, boolean isJsonRequest)  throws Exception {

        Assert.state(url != null && !"".equals(url), "url cannot be empty!");

        URI uri = new URI(url);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        ClientHttpRequest request = requestFactory.createRequest(uri, method);

        request.getHeaders().add(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");
        if (headers != null && headers.size() > 0) {
            MultiValueMap<String, String> httpHeaders = new HttpHeaders();

            Set<Map.Entry<String, String>> entries = headers.entrySet();
            for (Map.Entry<String, String> entry : entries){
                httpHeaders.add(entry.getKey(), entry.getValue());
            }

            request.getHeaders().addAll(httpHeaders);
        }

        if (params != null && params.size() > 0) {
            String paramStr;
            if (isJsonRequest) {
                request.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
                paramStr = JSON.toJSONString(params);
            } else {
                paramStr = params.toString();
            }
            request.getBody().write(paramStr.getBytes());
        }

        logger.info("sending http request...");
        logger.info("{}: {}", method, url);
        logger.info("params: {}", params);
        logger.info("headers: {}", request.getHeaders());

        ClientHttpResponse response = request.execute();
        HttpStatus statusCode = response.getStatusCode();

        logger.info("request complete. statusCode: {}", statusCode);

        if (HttpStatus.OK == statusCode) {
            InputStream body = response.getBody();

            InputStreamReader ins = new InputStreamReader(body);
            BufferedReader buffer = new BufferedReader(ins);

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = buffer.readLine()) != null){
                sb.append(line);
            }

            logger.info("response message: {}", sb.toString());
            return sb.toString();
        } else {
            logger.warn("Http Request error! Http status code {}", statusCode);
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>(4);
        params.put("name", "mjh");

        String url = "http://127.0.0.1:8080/hate/point";

        JSONObject result = get(url, params);

        System.out.println(result);
    }

}
