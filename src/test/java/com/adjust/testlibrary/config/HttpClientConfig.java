package com.adjust.testlibrary.config;

import org.springframework.context.annotation.Configuration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.context.annotation.Bean;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpConnectionManagerParams httpConnectionManagerParams() {
        HttpConnectionManagerParams connParams = new HttpConnectionManagerParams();
        connParams.setMaxTotalConnections(150);
        connParams.setDefaultMaxConnectionsPerHost(150);
        connParams.setConnectionTimeout(180000);
        connParams.setSoTimeout(180000);
        return connParams;
    }

    @Bean
    public MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager(HttpConnectionManagerParams httpConnectionManagerParams) {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.setParams(httpConnectionManagerParams);
        return connectionManager;
    }

    @Bean
    public HttpClient customHttpClient(MultiThreadedHttpConnectionManager connectionManager) {
        return new HttpClient((HttpConnectionManager)connectionManager);
    }
}
