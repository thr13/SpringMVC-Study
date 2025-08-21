package com.advanced.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 내부 호출 -> this 키워드가 생략되어 있음
 * 내부 호출은 실제 객체가 호출하므로 프록시 가 동작하지 않는다!!
 *
 */
@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        internal(); //사실상 this.internal(0
    }

    public void internal() {
        log.info("call internal");
    }
}
