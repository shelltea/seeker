/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.listener;

import java.util.Date;

import org.shelltea.seeker.entity.Channel;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.entity.Selector;
import org.shelltea.seeker.repository.AccountRepository;
import org.shelltea.seeker.repository.ChannelRepository;
import org.shelltea.seeker.repository.FeedRepository;
import org.shelltea.seeker.repository.SelectorRepository;
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
	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private ChannelRepository channelRepository;
	@Autowired
	private SelectorRepository selectorRepository;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			if (CONTEXT_DISPLAY_NAME.equals(((ContextRefreshedEvent) event).getApplicationContext().getDisplayName())) {
				logger.info("系统启动成功:)");

				// 初始化默认账户
				if (accountRepository.count() == 0) {
					accountService.initialize(accountService.create("shelltea@gmail.com", "shelltea", "shelltea"));
					logger.info("完成初始化默认账户");
				}

				// 初始化频道
				if (channelRepository.count() == 0) {
					channelRepository.save(new Channel("科技", "hdd", ""));
					channelRepository.save(new Channel("新闻", "globe", ""));
					logger.info("完成初始化频道");
				}

				// 初始化默认订阅源
				if (feedRepository.count() == 0) {
					// cnbeta
					Selector cnbetaRemoveSelector = new Selector(".content > .introduction > div");
					selectorRepository.save(cnbetaRemoveSelector);

					Feed cnbetaFeed = new Feed();
					cnbetaFeed.setTitle("cnBeta");
					cnbetaFeed.setUrl("http://www.cnbeta.com");
					cnbetaFeed.setFaviconUrl("http://www.cnbeta.com/favicon.ico");
					cnbetaFeed
							.setLogoUrl("http://easyread.ph.126.net/L7UMtMO6H4adgKkFh4SroQ==/3942338523811683680.png");
					cnbetaFeed.setFetchUrl("http://www.cnbeta.com");
					cnbetaFeed.setEntryUrlPrefix("http://www.cnbeta.com");
					cnbetaFeed.setListSelector("#allnews_all dt > a");
					cnbetaFeed.setTitleSelector("#news_title");
					cnbetaFeed.setOriginContentSelector(".content > .content");
					cnbetaFeed.setPublishedTimeSelector(".date");
					cnbetaFeed.setPublishedTimePattern("yyyy-MM-dd HH:mm:ss");
					cnbetaFeed.setAuthorSelector(".where > a");
					cnbetaFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
					cnbetaFeed.getRemoveSelectors().add(cnbetaRemoveSelector);
					feedRepository.save(cnbetaFeed);

					Channel techChannel = channelRepository.findByTitle("科技");
					techChannel.getFeeds().add(cnbetaFeed);
					channelRepository.save(techChannel);

					// ifeng
					Feed ifengFeed = new Feed();
					ifengFeed.setTitle("iFeng");
					ifengFeed.setUrl("http://www.ifeng.com/");
					ifengFeed.setFaviconUrl("http://y0.ifengimg.com/index/favicon.ico");
					ifengFeed.setLogoUrl("http://easyread.ph.126.net/zJJjqyFR2Wgncc11UNh-9Q==/3942619998790397121.png");
					ifengFeed.setFetchUrl("http://news.ifeng.com/rt-channel/rtlist_0");
					ifengFeed.setEntryUrlPrefix("");
					ifengFeed.setListSelector(".newsList ul a");
					ifengFeed.setTitleSelector("#artical_topic");
					ifengFeed.setOriginContentSelector("#main_content");
					ifengFeed.setPublishedTimeSelector("#artical_sth > p > span");
					ifengFeed.setPublishedTimePattern("yyyy年MM月dd日 HH:mm");
					ifengFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
					feedRepository.save(ifengFeed);

					Channel newsChannel = channelRepository.findByTitle("新闻");
					newsChannel.getFeeds().add(ifengFeed);
					channelRepository.save(newsChannel);

					logger.info("完成初始化默认订阅源");
				}
			}
		}
	}
}
