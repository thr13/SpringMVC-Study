package com.advanced.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 내부호출을 위해 별도로 분리한 클래스
 */
@Slf4j
@Component
public class InternalService {

    public void internal() {
        log.info("call internal");
    }

}
