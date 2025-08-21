package com.advanced.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ObjectProvider 는 스프링 컨테이너에서 객체 조회하는 것을 스프링 빈 생성 시점이 아닌, 실제 객체 사용 시점으로 지연시킬 수 있다
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {

    //private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public void external() {
        log.info("call external");
        // CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceProvider.getObject(); //호출 시점에 스프링 컨테이너에서 스프링 빈을 조회
        callServiceV2.internal();
    }
    public void internal() {
        log.info("call internal");
    }
}
