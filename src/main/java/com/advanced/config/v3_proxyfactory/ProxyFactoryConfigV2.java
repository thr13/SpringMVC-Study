package com.advanced.config.v3_proxyfactory;

import com.advanced.app.proxy.v2.ProxyOrderControllerV2;
import com.advanced.app.proxy.v2.ProxyOrderRepositoryV2;
import com.advanced.app.proxy.v2.ProxyOrderServiceV2;
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
public class ProxyFactoryConfigV2 {

    @Bean
    public ProxyOrderControllerV2 proxyFactoryOrderControllerV2(LogTrace logTrace) {
        ProxyOrderControllerV2 orderController = new ProxyOrderControllerV2(proxyFactoryOrderServiceV2(logTrace));
        ProxyFactory factory = new ProxyFactory(orderController);
        factory.addAdvisor(getAdvisor(logTrace));
        ProxyOrderControllerV2 proxy = (ProxyOrderControllerV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy, orderController.getClass());
        return proxy;
    }

    @Bean
    public ProxyOrderServiceV2 proxyFactoryOrderServiceV2(LogTrace logTrace) {
        ProxyOrderServiceV2 orderService = new ProxyOrderServiceV2(proxyFactoryOrderRepositoryV2(logTrace));
        ProxyFactory factory = new ProxyFactory(orderService);
        factory.addAdvisor(getAdvisor(logTrace));
        ProxyOrderServiceV2 proxy = (ProxyOrderServiceV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy, orderService.getClass());
        return proxy;
    }

    @Bean
    public ProxyOrderRepositoryV2 proxyFactoryOrderRepositoryV2(LogTrace logTrace) {
        ProxyOrderRepositoryV2 orderRepository = new ProxyOrderRepositoryV2();

        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvisor(getAdvisor(logTrace));
        ProxyOrderRepositoryV2 proxy = (ProxyOrderRepositoryV2) factory.getProxy();
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
