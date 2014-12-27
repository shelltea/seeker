package org.shelltea.seeker.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.Environment;
import reactor.rx.Promise;
import reactor.rx.Promises;

/**
 * Reactor Test.
 * <p>
 * Created by xiongshuhong on 14/12/3.
 */
public class ReactorTest {
    private static Logger logger = LoggerFactory.getLogger(ReactorTest.class);
    private static Environment environment;

    @Before
    public void init() {
        environment = new Environment();
    }

    @Test
    public void test1() {
        Promise<String> promise = Promises.prepare(environment);

        promise.onComplete(stringPromise -> logger.debug("length:{}", stringPromise.get()));

        promise.accept(RandomStringUtils.randomAlphanumeric(5));
    }

    @Test
    public void test2() {
        Promise<String> promise = Promises.success(environment, "success");

        promise.onComplete(stringPromise -> logger.debug("{}", stringPromise.get()));
    }
}
