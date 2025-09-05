package com.advanced.config.v2_dynamicproxy;

import com.advanced.app.proxy.v1.*;
import com.advanced.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.advanced.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import com.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public ProxyOrderControllerV1 dynamicProxyOrderControllerV1(LogTrace logTrace) {
        ProxyOrderControllerV1 orderController = new ProxyOrderControllerV1Impl(dynamicProxyOrderServiceV1(logTrace));

        ProxyOrderControllerV1 proxy = (ProxyOrderControllerV1) Proxy.newProxyInstance(ProxyOrderControllerV1.class.getClassLoader(),
                new Class[]{ProxyOrderControllerV1.class},
                new LogTraceFilterHandler(orderController, logTrace, PATTERNS));
        return proxy;
    }

    @Bean
    public ProxyOrderServiceV1 dynamicProxyOrderServiceV1(LogTrace logTrace) {
        ProxyOrderServiceV1 orderService = new ProxyOrderServiceV1Impl(dynamicProxyOrderRepositoryV1(logTrace));

        ProxyOrderServiceV1 proxy = (ProxyOrderServiceV1) Proxy.newProxyInstance(ProxyOrderServiceV1.class.getClassLoader(),
                new Class[]{ProxyOrderServiceV1.class},
                new LogTraceFilterHandler(orderService, logTrace, PATTERNS));
        return proxy;
    }

    @Bean
    public ProxyOrderRepositoryV1 dynamicProxyOrderRepositoryV1(LogTrace logTrace) {
        ProxyOrderRepositoryV1 orderRepository = new ProxyOrderRepositoryV1Impl();

        ProxyOrderRepositoryV1 proxy = (ProxyOrderRepositoryV1) Proxy.newProxyInstance(ProxyOrderRepositoryV1.class.getClassLoader(),
                new Class[]{ProxyOrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS));
        return proxy;
    }
}
