/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.listener;

import org.shelltea.seeker.repository.AccountRepository;
import org.shelltea.seeker.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 基础数据监听器。
 * <p>
 * 当数据库中不存在基础数据时，自动执行初始化。
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
public class InitListener implements ApplicationListener<ApplicationEvent> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String CONTEXT_DISPLAY_NAME = "Root WebApplicationContext";

	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			if (CONTEXT_DISPLAY_NAME.equals(((ContextRefreshedEvent) event).getApplicationContext().getDisplayName())) {
				logger.info("系统启动成功:)");

				// 执行初始化
				if (accountRepository.count() == 0) {
					accountService.initialize(accountService.create("shelltea@gmail.com", "shelltea", "shelltea"));
					logger.info("初始化默认账户");
				}
			}
		}
	}
}
