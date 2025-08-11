package com.advanced.app.proxy.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProxyOrderControllerV2 {

    private final ProxyOrderServiceV2 orderService;

    public ProxyOrderControllerV2(ProxyOrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/proxy/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/proxy/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
