package com.advanced.config;

import com.advanced.app.proxy.v2.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV2Config {

    @Bean
    public ProxyOrderControllerV2 proxyOrderControllerV2() {
        return new ProxyOrderControllerV2(proxyOrderServiceV2());
    }

    @Bean
    public ProxyOrderServiceV2 proxyOrderServiceV2() {
        return new ProxyOrderServiceV2(proxyOrderRepositoryV2());
    }

    @Bean
    public ProxyOrderRepositoryV2 proxyOrderRepositoryV2() {
        return new ProxyOrderRepositoryV2();
    }
}
