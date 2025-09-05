package com.advanced.config.v6_aop;

import com.advanced.config.AppV1Config;
import com.advanced.config.AppV2Config;
import com.advanced.config.v6_aop.aspect.LogTraceAspect;
import com.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
