package com.advanced.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Pointcut 어노테이션으로 pointcut 을 분리할 수 있다
 */
@Slf4j
@Aspect
public class AspectV2 {

    //com.advanced.order 패키지 및 하위 패키지 적용 -> 이렇게 포인트컷을 메소드 단위로 분리해서 사용할 수 있는 것을 pointcut signature 라고 한다
    @Pointcut("execution(* com.advanced.order..*(..))")
    private void allOrder() {}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //joinPoint signature
        return joinPoint.proceed();
    }
}
