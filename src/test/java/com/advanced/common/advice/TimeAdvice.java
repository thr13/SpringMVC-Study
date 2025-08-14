package com.advanced.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 프록시팩토리는 프록시를 생성하는 단계에서 target 정보를 파라미터로 전달한다
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {



    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed(); //invocation 내부에서 자동으로 target 클래스를 호출한다

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
