package com.advanced.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 모든 Advice 는 joinPoint 를 첫번째 파라미터로 사용할 수 있다(생략가능)
 * client - @Around - @Before - @After - @AfterReturning - @AfterThrowing - server 순서로 주고 받는다(호출 순서와 리턴 순서는 반대)
 * 주의: @Around 는 ProceedingJoinPoint 를 사용해야 한다(joinPoint.proceed() 호출 필수)
 */
@Slf4j
@Aspect
public class AspectV6 {

    @Around("com.advanced.order.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("com.advanced.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //@Before: joinPoint 실행 이전에 실행
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning: joinPoint 정상 실행 완료후 실행
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing: 메서드가 예외를 던지는 경우 실행됨
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After: joinPoint 정상 실행 및 예외 상황과 관계없이 항상 실행됨
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    //@Before 는 joinPoint 가 실행하기 직전까지만 작성
    @Before("com.advanced.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.advanced.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        //주의: @AfterReturning 의 returning 에 지정된 객체는 변경할 수 없다
        log.info("[result] {}, result={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.advanced.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {}, message={}", joinPoint.getSignature(), ex.getMessage());
    }

    @After("com.advanced.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
