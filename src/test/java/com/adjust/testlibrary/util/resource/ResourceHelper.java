package com.adjust.testlibrary.util.resource;


import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.util.JSONUtils;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ResourceHelper {

    private static final Logger logger = LoggerFactory.getLogger(ResourceHelper.class);

    @Autowired
    private HttpClientPool httpClientPool;

    @Value("${petstore.api.key}")
    private String CRM_APP_KEY;

    public Response get(String path) {
        return get(path, null, null, null);
    }

    public Response get(String path, Pageable pageable) {
        return get(path, pageable, null, null);
    }

    public Response get(String path, Pageable pageable, Map<String, String> queryParams) {
        return get(path, pageable, queryParams, null);
    }

    public Response get(String path, HttpHeaders headers) {
        return get(path, null, null, headers);
    }

    public Response get(String path, Pageable pageable, Map<String, String> queryParams, HttpHeaders headers) {
        Map<String, String> params = new HashMap<>();
        if (null != pageable) {
            params.put("page", String.valueOf(pageable.getPageNumber()));
            params.put("size", String.valueOf(pageable.getPageSize()));
        }
        if (null != queryParams)
            queryParams.keySet().forEach(key -> params.put(key, (String)queryParams.get(key)));
        return request(Method.GET, path, headers, null, params);
    }

    public Response post(String path, Object body) {
        return post(path, body, (HttpHeaders)null);
    }

    public Response post(String path, Object body, Map<String, String> params) {
        String json = (null != body) ? JSONUtils.writeJSON(body) : null;
        return request(Method.POST, path, null, json, params);
    }

    public Response post(String path, Object body, HttpHeaders headers) {
        String json = (null != body) ? JSONUtils.writeJSON(body) : null;
        return request(Method.POST, path, headers, json);
    }

    public Response post(String path, Object body, HttpHeaders headers, Map<String, String> params)
    {
        String json = (null != body) ? JSONUtils.writeJSON(body) : null;
        return request(Method.POST, path, headers, json, params);
    }

    public Response put(String path, Object body) {
        return put(path, body, null);
    }

    public Response put(String path, Object body, HttpHeaders headers) {
        String json = (null != body) ? JSONUtils.writeJSON(body) : null;
        return request(Method.PUT, path, headers, json);
    }

    public Response delete(String path) {
        return request(Method.DELETE, path, null, null);
    }

    private enum Method {
        GET, POST, PUT, DELETE;
    }

    private Response request(Method method, String url, HttpHeaders headers, String content) {
        return request(method, url, headers, content, null);
    }

    private Response request(Method method, String url, HttpHeaders headers, String content, Map<String, String> params) {
        logger.info("About to perform HTTP {} to URL {}", method, url);
        if (headers == null)
            headers = getRequestHeaders();
        switch (method) {
            case GET:
                return this.httpClientPool.get(url, params, headers);
            case POST:
                return this.httpClientPool.post(url, content, "application/json", headers, params);
            case DELETE:
                return this.httpClientPool.delete(url, headers);
            case PUT:
                return this.httpClientPool.put(url, content, "application/json", headers);
        }
        throw new RuntimeException("Unsupported method: " + method);
    }

    public HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_key", CRM_APP_KEY);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        return headers;
    }
}
