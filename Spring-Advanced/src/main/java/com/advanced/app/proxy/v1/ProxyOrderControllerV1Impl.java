package com.advanced.app.proxy.v1;

public class ProxyOrderControllerV1Impl implements ProxyOrderControllerV1 {

    private final ProxyOrderServiceV1 orderService;

    public ProxyOrderControllerV1Impl(ProxyOrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
