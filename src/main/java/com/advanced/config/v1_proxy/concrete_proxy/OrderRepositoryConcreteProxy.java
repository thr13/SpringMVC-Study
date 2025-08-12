package com.advanced.config.v1_proxy.concrete_proxy;

import com.advanced.app.proxy.v2.ProxyOrderRepositoryV2;
import com.advanced.trace.TraceStatus;
import com.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

public class OrderRepositoryConcreteProxy extends ProxyOrderRepositoryV2 {

    private final ProxyOrderRepositoryV2 target;
    private final LogTrace logTrace;


    public OrderRepositoryConcreteProxy(ProxyOrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            //target 호출
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}