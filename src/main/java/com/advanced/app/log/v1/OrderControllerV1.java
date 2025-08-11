package com.advanced.app.log.v1;

import com.advanced.app.log.hellotrace.HelloTraceV1;
import com.advanced.trace.TraceStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    public OrderControllerV1(OrderServiceV1 orderService, HelloTraceV1 trace) {
        this.orderService = orderService;
        this.trace = trace;
    }

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외가 터진 경우, 예외 로그 출력 후 애플리케이션 흐름이 종료되지 않게 다시 예외를 던져야 한다
        }
    }
}
