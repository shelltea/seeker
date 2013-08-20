/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import java.util.NoSuchElementException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件工具类.
 * <p>
 * 按照applicationContext.xml中定义的配置文件优先级加载应用配置.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public abstract class PropertiesUtils {
	/**
	 * 日志.
	 */
	protected final static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	// 常用参数
	/**
	 * jdbc地址.
	 */
	public static final String JDBC_URL_PROPERTY = "jdbc.url";
	/**
	 * jdbc用户名.
	 */
	public static final String JDBC_USERNAME_PROPERTY = "jdbc.username";
	/**
	 * jdbc密码.
	 */
	public static final String JDBC_PASSWORD_PROPERTY = "jdbc.password";
	/**
	 * jdbc驱动.
	 */
	public static final String JDBC_DRIVER_PROPERTY = "jdbc.driver";

	// 配置文件名，优先级从小到大
	/**
	 * 生产环境配置文件名.
	 */
	private static final String SERVER_PROPERTIES_FILE = "application.server.properties";
	/**
	 * 本地配置文件名.
	 */
	private static final String LOCAL_PROPERTIES_FILE = "application.local.properties";

	// 配置文件
	/**
	 * 生产环境配置.
	 */
	private static PropertiesConfiguration serverConfiguration;
	/**
	 * 本地配置.
	 */
	private static PropertiesConfiguration localConfiguration;

	static {
		loadConfigFiles();
	}

	/**
	 * 获取布尔值.
	 * 
	 * @param key
	 *            键名
	 * @return 布尔值
	 */
	public static Boolean getBoolean(final String key) {
		if (localConfiguration != null && localConfiguration.containsKey(key)) {
			return localConfiguration.getBoolean(key);
		}
		if (serverConfiguration != null && serverConfiguration.containsKey(key)) {
			return serverConfiguration.getBoolean(key);
		}

		return Boolean.FALSE;
	}

	/**
	 * 获取整型.
	 * 
	 * @param key
	 *            键名
	 * @return 整型
	 */
	public static int getInt(final String key) {
		if (localConfiguration != null && localConfiguration.containsKey(key)) {
			return localConfiguration.getInt(key);
		}
		if (serverConfiguration != null && serverConfiguration.containsKey(key)) {
			return serverConfiguration.getInt(key);
		}

		throw new NoSuchElementException("没找到" + key + "对应的整数值");
	}

	/**
	 * 获取字符串值.
	 * 
	 * @param key
	 *            键名
	 * @return 字符串值
	 */
	public static String getString(final String key) {
		if (localConfiguration != null && localConfiguration.containsKey(key)) {
			return localConfiguration.getString(key);
		}
		if (serverConfiguration != null && serverConfiguration.containsKey(key)) {
			return serverConfiguration.getString(key);
		}

		return null;
	}

	/**
	 * 加载配置文件.
	 */
	private static void loadConfigFiles() {
		// 加载服务器配置文件
		try {
			serverConfiguration = new PropertiesConfiguration(SERVER_PROPERTIES_FILE);
		} catch (ConfigurationException e) {
			logger.debug("无服务器配置文件.");
		}

		// 加载本地配置文件
		try {
			localConfiguration = new PropertiesConfiguration(LOCAL_PROPERTIES_FILE);
		} catch (ConfigurationException e) {
			logger.debug("无本地配置文件.");
		}
	}
}
