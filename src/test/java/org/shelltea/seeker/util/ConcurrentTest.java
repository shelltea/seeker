package org.shelltea.seeker.util;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并发包测试用例.
 * Created by xiongshuhong on 14/12/19.
 */
public class ConcurrentTest {
    private static Logger logger = LoggerFactory.getLogger(ConcurrentTest.class);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1000);

    @Before
    public void init() {

    }

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        Future<LocalDateTime> future = cachedThreadPool.submit((Callable<LocalDateTime>) LocalDateTime::now);
        logger.debug("{}", future.get());
    }

    @Test
    public void testFutureList() throws InterruptedException, ExecutionException {
        List<Callable<Instant>> callables = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                int random = RandomUtils.nextInt(10);
                logger.debug("random:{}", random);
                Thread.sleep(random * 1000);
                return Instant.now();
            });
        }

        List<Future<Instant>> futures = cachedThreadPool.invokeAll(callables);

        for (Future<Instant> future : futures) {
            logger.debug("{}", future.get().toString());
        }
    }

    @Test
    public void testCountDownLatch() throws InterruptedException {
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);

        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = () -> {
                try {
                    begin.await();//一直阻塞
                    Thread.sleep((long) (Math.random() * 10000));
                    logger.debug("No.{} arrived", NO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            };
            fixedThreadPool.submit(run);
        }
        logger.debug("Game Start");
        begin.countDown();
        end.await();
        logger.debug("Game Over");
        fixedThreadPool.shutdown();
    }

    @Test
    public void testAtomic() throws InterruptedException, ExecutionException {
        Count count1 = new Count(0);
        Count count2 = new Count(0);

        List<Callable<Integer>> callables = Lists.newArrayList();

        for (int i = 0; i < 1000; i++) {
            callables.add(() -> {
                count1.add();
                count2.add();
                Count.add2();
                return 0;
            });
        }

        fixedThreadPool.invokeAll(callables);

        logger.debug("{}", Count.count2);
        logger.debug("{}", count1.count);
        logger.debug("{}", count2.count);
    }

    @Test
    public void testSynchronized() {
        Integer i = 0;

        Stopwatch stopwatch = Stopwatch.createStarted();
        synchronized (i) {
            i++;
        }
        logger.debug("{}", stopwatch.stop());

        stopwatch.reset().start();
        i++;
        logger.debug("{}", stopwatch.stop());
    }
}

class Count {
    int count;
    static int count2;

    public Count(int count) {
        this.count = count;
    }

    public void add() {
        if (this.count < 500) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.count++;
        }
    }

    public static synchronized void add2() {
        if (count2 < 500) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count2++;
        }
    }
}
