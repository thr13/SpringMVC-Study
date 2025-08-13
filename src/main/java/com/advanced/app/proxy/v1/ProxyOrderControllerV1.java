package com.advanced.app.proxy.v1;

import org.springframework.web.bind.annotation.*;

//@RestController
public interface ProxyOrderControllerV1 {

    @GetMapping("/proxy/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/proxy/v1/no-log")
    String noLog();
}
