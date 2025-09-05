package com.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 변하지 않는 부분을 추상 클래스로 선언
 */
@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        call(); //추상 클래스를 상속해서, 해당 추상메소드를 자식클래스에서 임의대로 구현할 수 있다
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    protected abstract void call();
}
