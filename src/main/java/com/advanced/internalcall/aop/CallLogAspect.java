package com.advanced.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * CallServiceV0 에 AOP 적용할 Aspect
 */
@Slf4j
@Aspect
public class CallLogAspect {

    @Before("execution(* com.advanced.internalcall..*.*(..))")
    public void doLog(JoinPoint joinPoint) {
        log.info("aop={}", joinPoint.getSignature());
    }
}
