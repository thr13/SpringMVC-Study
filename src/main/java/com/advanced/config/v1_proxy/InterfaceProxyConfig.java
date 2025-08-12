package com.advanced.config.v1_proxy;

import com.advanced.app.proxy.v1.*;
import com.advanced.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.advanced.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.advanced.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 실제 객체의 스프링 빈 대신 설정한 프록시를 등록하여 프록시를 생성하고 사용한다(실제 객체는 스프링 빈으로 등록하지 않는다)
 * 프록시 객체가 실제 객체를 참조하기 때문에 프록시를 통해서 실제 객체를 호출할 수 있다(프록시 객체 안에 실제 객체가 존재함)
 */
@Configuration
public class InterfaceProxyConfig {

    @Bean
    public ProxyOrderControllerV1 interfaceProxyOrderController(LogTrace logTrace) {
        ProxyOrderControllerV1Impl controllerImpl = new ProxyOrderControllerV1Impl(interfaceProxyOrderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }
    @Bean
    public ProxyOrderServiceV1 interfaceProxyOrderService(LogTrace logTrace) {
        ProxyOrderServiceV1Impl serviceImpl = new ProxyOrderServiceV1Impl(interfaceProxyOrderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }
    @Bean
    public ProxyOrderRepositoryV1 interfaceProxyOrderRepository(LogTrace logTrace) {
        ProxyOrderRepositoryV1Impl repositoryImpl = new ProxyOrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }

}
