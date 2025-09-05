package com.advanced.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 프록시는 체인이 될 수 있으므로 데코레이터를 여러개 추가할 수 있다
 */
@Slf4j
public class TimeDecorator implements Component {

    private Component component;

    public TimeDecorator (Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();

        String result = component.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
        return result;
    }
}
