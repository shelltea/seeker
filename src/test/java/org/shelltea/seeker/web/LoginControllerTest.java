/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class LoginControllerTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testLoginPost() throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();

		List<NameValuePair> formParams = Lists.newArrayList();
		formParams.add(new BasicNameValuePair("username", "shelltea"));
		formParams.add(new BasicNameValuePair("password", "shelltea"));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
		HttpPost httpPost = new HttpPost("http://localhost:8080/account/login");
		httpPost.setEntity(entity);

		HttpResponse httpResponse = httpClient.execute(httpPost);

		logger.debug("{}", httpResponse);
	}
}
