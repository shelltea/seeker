/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.google.common.collect.Maps;

/**
 * 验证工具类.
 * <p>
 * 重新渲染BindingResult对象，将数据绑定错误和验证错误统一.
 * 
 * @author Xiong Shuhong
 */
public abstract class ValidationUtils {
	/**
	 * 分隔符.
	 */
	private static final String SEPARATOR = ".";

	/**
	 * 重新封装BindingResult绑定验证结果，将绑定结果和验证结果统一到一起返回.
	 * <p>
	 * 如果没有绑定结果信息，messageSource可为<code>null</code>
	 * <p>
	 * 返回结果Map的key为field,value为message
	 * 
	 * @param bindingResult
	 *            BindingResult对象
	 * @param messageSource
	 *            绑定信息资源
	 * @return 验证结果
	 */
	public static Map<String, String> renderResultMap(final BindingResult bindingResult,
			final MessageSource messageSource) {
		return renderResultMap(bindingResult, messageSource, Locale.getDefault());
	}

	/**
	 * 重新封装BindingResult绑定验证结果，将绑定结果和验证结果统一到一起返回.
	 * <p>
	 * 如果没有绑定结果信息，messageSource可为<code>null</code>
	 * <p>
	 * 返回结果Map的key为field,value为message
	 * 
	 * @param bindingResult
	 *            BindingResult对象
	 * @param messageSource
	 *            绑定信息资源
	 * @param locale
	 *            地区
	 * @return 验证结果
	 */
	public static Map<String, String> renderResultMap(final BindingResult bindingResult,
			final MessageSource messageSource, final Locale locale) {
		Map<String, String> resultMap = Maps.newHashMap();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			try {
				if (messageSource != null) {
					String message = messageSource.getMessage(getKey(fieldError), null, locale);
					resultMap.put(fieldError.getField(), message);
				} else {
					resultMap.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
			} catch (NoSuchMessageException e) {
				resultMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		return resultMap;
	}

	/**
	 * 根据Key从MessageSource中获取验证消息.
	 * 
	 * @param key
	 *            键
	 * @param messageSource
	 *            消息源
	 * @return 验证消息
	 */
	public static Map<String, String> renderResultMap(final String key, final MessageSource messageSource) {
		return renderResultMap(key, messageSource, Locale.getDefault());
	}

	/**
	 * 根据Key从MessageSource中获取验证消息.
	 * 
	 * @param key
	 *            键
	 * @param messageSource
	 *            消息源
	 * @param locale
	 *            地区
	 * @return 验证消息
	 */
	public static Map<String, String> renderResultMap(final String key, final MessageSource messageSource,
			final Locale locale) {
		Map<String, String> resultMap = Maps.newHashMap();
		String message = messageSource.getMessage(key, null, locale);
		resultMap.put(key.split("\\.")[2], message);
		return resultMap;
	}

	/**
	 * 构建验证信息在资源文件中的key值.
	 * <p>
	 * 例如：<code>NotNull.person.name</code>
	 * 
	 * @param fieldError
	 *            FieldError对象
	 * @return key值
	 */
	private static String getKey(final FieldError fieldError) {
		return new StringBuilder().append(fieldError.getCode()).append(SEPARATOR).append(fieldError.getObjectName())
				.append(SEPARATOR).append(fieldError.getField()).toString();
	}
}
