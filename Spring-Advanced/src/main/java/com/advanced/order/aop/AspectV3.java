package com.advanced.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * aspectj 는 *패키지명 *클래스명 *메소드명 순으로 이뤄진다 (..) 은 하위 경로에 모두 적용하는 것을 의미한다
 */
@Slf4j
@Aspect
public class AspectV3 {

    //com.advanced.order 패키지 및 하위 패키지 적용 -> 이렇게 포인트컷을 메소드 단위로 분리해서 사용할 수 있는 것을 pointcut signature 라고 한다
    @Pointcut("execution(* com.advanced.order..*(..))")
    private void allOrder() {}

    //클래스 이름 패턴이 *Service 로 끝나는 경우
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //joinPoint signature
        return joinPoint.proceed();
    }

    //com.advanced.order 패키지 및 하위 패키지에 속하면서 클래스 이름 패턴이 *Service 로 끝나는 클래스, 인터페이스 적용
    @Around("allService()")
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
