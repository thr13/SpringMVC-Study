package com.advanced;

import com.advanced.config.v6_aop.AopConfig;
import com.advanced.trace.logtrace.LogTrace;
import com.advanced.trace.logtrace.ThreadLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({InterfaceProxyConfig.class, AppV2Config.class})
//@Import(ConcreteProxyConfig.class)
//@Import({DynamicProxyBasicConfig.class, AppV2Config.class})
//@Import({DynamicProxyFilterConfig.class, AppV2Config.class})
//@Import({ProxyFactoryConfigV1.class, AppV2Config.class})
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
//@Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "com.advanced.app")
public class AdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLogTrace();
    }
}
