package com.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * context 는 변하지 않는 부분을 저장한다
 * 전락 패턴은 필드에 전략(인터페이스)를 보관하고 생성자를 통해 주입을 받는다
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
