/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import java.lang.reflect.Method;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class ReflectionTest {
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> clazz = java.lang.Class.forName("java.lang.String");
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
	}
}
