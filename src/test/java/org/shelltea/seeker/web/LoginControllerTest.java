/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web;

import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class LoginControllerTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testLoginPost() throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

        List<NameValuePair> formParams = Lists.newArrayList();
        formParams.add(new BasicNameValuePair("username", "shelltea"));
        formParams.add(new BasicNameValuePair("password", "shelltea"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
        HttpPost httpPost = new HttpPost("http://localhost:8080/account/login");
        httpPost.setEntity(entity);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

        logger.debug("{}", closeableHttpResponse);
    }
}
