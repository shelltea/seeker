package org.shelltea.seeker.util;

import com.google.common.collect.Lists;
import hirondelle.date4j.DateTime;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.*;

/**
 * 并发包测试用例.
 * Created by xiongshuhong on 14/12/19.
 */
public class ConcurrentTest {
    private static Logger logger = LoggerFactory.getLogger(ConcurrentTest.class);
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Before
    public void init() {

    }

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        Future<DateTime> future = executorService.submit(() -> DateTime.now(TimeZone.getDefault()));
        logger.debug(future.get().toString());
    }

    @Test
    public void testFutureList() throws InterruptedException, ExecutionException {
        List<Callable<DateTime>> callables = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                int random = RandomUtils.nextInt(10);
                logger.debug("random:{}", random);
                Thread.sleep(random * 1000);
                return DateTime.now(TimeZone.getDefault());
            });
        }

        List<Future<DateTime>> futures = executorService.invokeAll(callables);

        for (Future<DateTime> future : futures) {
            logger.debug("{}", future.get().toString());
        }
    }
}
