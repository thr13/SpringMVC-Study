package com.advanced.config.v1_proxy;

import com.advanced.app.proxy.v2.*;
import com.advanced.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.advanced.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.advanced.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public ProxyOrderControllerV2 interfaceProxyOrderController(LogTrace logTrace) {
        ProxyOrderControllerV2 controllerImpl = new ProxyOrderControllerV2(interfaceProxyOrderService(logTrace));
        return new OrderControllerConcreteProxy(controllerImpl, logTrace);
    }
    @Bean
    public ProxyOrderServiceV2 interfaceProxyOrderService(LogTrace logTrace) {
        ProxyOrderServiceV2 serviceImpl = new ProxyOrderServiceV2(interfaceProxyOrderRepository(logTrace));
        return new OrderServiceConcreteProxy(serviceImpl, logTrace);
    }
    @Bean
    public ProxyOrderRepositoryV2 interfaceProxyOrderRepository(LogTrace logTrace) {
        ProxyOrderRepositoryV2 repositoryImpl = new ProxyOrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, logTrace);
    }
}
