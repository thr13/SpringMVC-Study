package com.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;


/**
 * 변하는 부분을 추상 클래스의 구현체에 상속과 오버라이딩으로 구현
 */
@Slf4j
public class SubClassLogic2 extends AbstractTemplate {

    @Override
    protected void call() {
        log.info("비즈니스 로직2 실행");
    }
}
