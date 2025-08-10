package com.advanced.app.proxy.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface ProxyOrderControllerV1 {

    @GetMapping("/proxy/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/proxy/v1/no-log")
    String noLog();
}
