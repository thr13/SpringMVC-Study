package com.advanced.config.v3_proxyfactory;

import com.advanced.app.proxy.v1.*;
import com.advanced.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public ProxyOrderControllerV1 proxyOrderControllerV1(LogTrace logTrace) {
        ProxyOrderControllerV1 orderController = new ProxyOrderControllerV1Impl(proxyOrderServiceV1(logTrace));
        ProxyFactory factory = new ProxyFactory(orderController);
        factory.addAdvisor(getAdvisor(logTrace));
        ProxyOrderControllerV1 proxy = (ProxyOrderControllerV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy, orderController.getClass());
        return proxy;
    }

    @Bean
    public ProxyOrderServiceV1 proxyOrderServiceV1(LogTrace logTrace) {
        ProxyOrderServiceV1 orderService = new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1(logTrace));
        ProxyFactory factory = new ProxyFactory(orderService);
        factory.addAdvisor(getAdvisor(logTrace));
        ProxyOrderServiceV1 proxy = (ProxyOrderServiceV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy, orderService.getClass());
        return proxy;
    }

    @Bean
    public ProxyOrderRepositoryV1 proxyOrderRepositoryV1(LogTrace logTrace) {
        ProxyOrderRepositoryV1Impl orderRepository = new ProxyOrderRepositoryV1Impl();

        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvisor(getAdvisor(logTrace));
        ProxyOrderRepositoryV1 proxy = (ProxyOrderRepositoryV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy, orderRepository.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
