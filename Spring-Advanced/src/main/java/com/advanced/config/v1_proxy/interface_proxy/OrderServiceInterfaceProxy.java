package com.advanced.config.v1_proxy.interface_proxy;

import com.advanced.app.proxy.v1.ProxyOrderServiceV1;
import com.advanced.trace.TraceStatus;
import com.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements ProxyOrderServiceV1 {

    private final ProxyOrderServiceV1 target;
    private final LogTrace logTrace;

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.request()");
            //target 호출(실제 객체)
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception  (status, e);
            throw e;
        }
    }
}
