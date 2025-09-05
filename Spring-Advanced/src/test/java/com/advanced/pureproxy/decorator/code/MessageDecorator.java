package com.advanced.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 데코레이터 패턴: 원래 서버가 제공하는 기능에 더해서 부가 기능을 수행한다 (프록시 자리)
 */
@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component=component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        //data -> *****data***** 로 변경
        String result = component.operation();
        String decoResult = "*****" + result + "*****";
        log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult);
        return decoResult;
    }
}
