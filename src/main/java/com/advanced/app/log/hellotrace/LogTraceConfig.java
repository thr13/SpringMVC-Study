package com.advanced.app.log.hellotrace;

import com.advanced.trace.logtrace.LogTrace;
import com.advanced.trace.logtrace.ThreadLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLogTrace();
    }
}
