package com.advanced.app.proxy.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyOrderControllerV3 {

    private final ProxyOrderServiceV3 orderService;

    public ProxyOrderControllerV3(ProxyOrderServiceV3 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/proxy/v3/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/proxy/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
