package com.advanced.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/**
 * Advice 는 기본적으로 순서를 보장하지 않는다
 * 순서를 지정하고 싶다면 @Order 어노테이션을 사용해야한다 그러나 @Order 는 클래스 단위로 적용될 수 있으므로 하나의 Aspect 에 여러 Advice 가 존재할 경우 순서를 보장 받을 수 없다
 * 그러므로 Aspect 를 별도의 클래스로 분리해야한다
 */
@Slf4j
@Aspect
public class AspectV5 {

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("com.advanced.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        @Around("com.advanced.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
