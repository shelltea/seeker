package org.shelltea.seeker.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;
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
        Reactor reactor = Reactors.reactor().env(environment).dispatcher(Environment.RING_BUFFER).get();

        reactor.on(Selectors.$("test"), new Consumer<Event<String>>() {
            @Override
            public void accept(Event<String> event) {
                logger.debug("accept:{}", event.getData());
            }
        });

        reactor.notify("test", Event.wrap("data"));
    }

    @Test
    public void test2() {
        Promise<String> promise = Promises.defer(environment);

        promise.onComplete(new Consumer<Promise<String>>() {
            @Override
            public void accept(Promise<String> stringPromise) {
                logger.debug("length:{}", stringPromise.get());

            }
        });

        promise.accept(RandomStringUtils.randomAlphanumeric(5));
    }

    @Test
    public void test3() {
        Promise<String> promise = Promises.success(environment, "success");

        promise.onComplete(new Consumer<Promise<String>>() {
            @Override
            public void accept(Promise<String> stringPromise) {
                System.out.println(1 + stringPromise.get());
            }
        });
    }
}
