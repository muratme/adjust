package com.adjust.testlibrary.util.resource;

import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.model.base.ResponseError;
import com.adjust.testlibrary.util.JSONUtils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HttpClientPool {

    @Autowired
    private HttpClient client;

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int DEFAULT_TIMEOUT = 10000;

    public Response get(String url) {
        return get(url, null, null);
    }

    public Response get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    public Response get(String url, Map<String, String> params, HttpHeaders headers) {
        GetMethod get = new GetMethod(url);
        if (params != null && !params.isEmpty())
            get.setQueryString(getParams(params));
        addHeaders(headers, (HttpMethod)get);
        return connect((HttpMethod)get);
    }

    public Response post(String url) {
        return post(url, new HashMap<>(), (HttpHeaders)null, DEFAULT_TIMEOUT);
    }

    public Response post(String url, Map<String, String> params) {
        return post(url, params, (HttpHeaders)null, DEFAULT_TIMEOUT);
    }

    public Response post(String url, Map<String, String> params, int timeout) {
        return post(url, params, (HttpHeaders)null, timeout);
    }

    public Response post(String url, Map<String, String> params, HttpHeaders headers) {
        return post(url, params, headers, DEFAULT_TIMEOUT);
    }

    public Response post(String url, String content, String contentType) {
        return post(url, content, contentType, (HttpHeaders)null);
    }

    public Response post(String url, Map<String, String> params, HttpHeaders headers, int timeout) {
        PostMethod post = new PostMethod(url);
        post.getParams().setSoTimeout(timeout);
        post.setRequestBody(getParams(params));
        addHeaders(headers, (HttpMethod)post);
        return connect((HttpMethod)post);
    }

    public Response post(String url, String content, String contentType, HttpHeaders headers) {
        return post(url, content, contentType, headers, null);
    }

    public Response post(String url, String content, String contentType, HttpHeaders headers, Map<String, String> params) {
        PostMethod post = new PostMethod(url);
        if (params != null && !params.isEmpty())
            post.setQueryString(getParams(params));
        try {
            if (content == null)
                content = "";
            post.setRequestEntity((RequestEntity)new StringRequestEntity(content, contentType, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("unsupported charset", e);
        }
        addHeaders(headers, (HttpMethod)post);
        return connect((HttpMethod)post);
    }

    public Response post(String url, InputStream content, String contentType, HttpHeaders headers) {
        if (content == null)
            throw new IllegalArgumentException("content stream may not be null");
        PostMethod post = new PostMethod(url);
        post.setRequestEntity((RequestEntity)new InputStreamRequestEntity(content, contentType));
        addHeaders(headers, (HttpMethod)post);
        return connect((HttpMethod)post);
    }

    public Response put(String url, String content, String contentType) {
        return put(url, content, contentType, null);
    }

    public Response put(String url, String content, String contentType, HttpHeaders headers) {
        PutMethod put = new PutMethod(url);
        try {
            put.setRequestEntity((RequestEntity)new StringRequestEntity(content, contentType, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("unsupported charset", e);
        }
        addHeaders(headers, (HttpMethod)put);
        return connect((HttpMethod)put);
    }

    public Response delete(String url, HttpHeaders headers) {
        DeleteMethod delete = new DeleteMethod(url);
        addHeaders(headers, (HttpMethod)delete);
        return connect((HttpMethod)delete);
    }

    public Response delete(String url) {
        DeleteMethod delete = new DeleteMethod(url);
        return connect((HttpMethod)delete);
    }

    private void addHeaders(HttpHeaders headers, HttpMethod method) {
        if (null != headers)
            headers.keySet().forEach(header -> method.addRequestHeader(header, headers.get(header)));
    }

    private Response connect(HttpMethod method) {
        Response response = new Response();
        ResponseError responseError = new ResponseError();
        try {
            int statusCode = this.client.executeMethod(method);
            if (!HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                responseError.setCode(statusCode);
                responseError.setMessage(method.getResponseBodyAsString());
                response.setSuccess(false);
                response.setError(responseError);
            } else {
                if (response.isSuccess() && null == response.getData()) {
                    try {
                        response.setData(JSONUtils.readJSON(method.getResponseBodyAsString(), JsonNode.class));
                    }catch (Exception e)
                    {
                        response.setData(JSONUtils.readJSON(JSONUtils.writeJSON(method.getResponseBodyAsString()), JsonNode.class));
                    }
                }
            }
        } catch (IOException e) {
            responseError.setMessage(e.getMessage());
            response.setError(responseError);
            response.setSuccess(false);
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    private NameValuePair[] getParams(Map<String, String> params) {
        return (NameValuePair[])params.keySet()
                .stream()
                .map(key -> new NameValuePair(key, (String)params.get(key)))
                .toArray(NameValuePair[]::new);
    }

}
