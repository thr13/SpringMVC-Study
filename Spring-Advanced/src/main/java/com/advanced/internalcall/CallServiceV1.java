package com.advanced.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 프록시 내부 호출 대안 -> 자기 자신 주입
 * 단, 생성자 주입은 순환 사이클을 만드므로 주의해야한다
 */
@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    //스프링 AOP 가 적용된 대상(CallServiceV1)을 의존관계 주입을 받으면, 주입 대상은 실제 자신이 아닌 프록시 객체
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); //this.internal() 이 아닌, 의존관계 주입을 받은 callServiceV1.internal() 호출 -> 프록시를 통해 AOP 적용
    }

    public void internal() {
        log.info("call internal");
    }
}
