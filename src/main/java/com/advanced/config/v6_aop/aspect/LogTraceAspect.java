package com.advanced.config.v6_aop.aspect;

import com.advanced.trace.TraceStatus;
import com.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Aspect 는 애테이션 기반 프록시 적용 선언
 * @Around 의 값으로 AspectJ 표현식을 넣어 Pointcut 을 지정할 수 있다 그리고 @Around 가 붙은 메소드는 Advice 가 된다
 * 즉, @Around 가 붙은 메소드는 Advisor 가 될 수 있다
 */
@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around("execution(* com.advanced.app.proxy..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;

        log.info("target={}", joinPoint.getTarget()); //실제 호출 대상
        log.info("getArgs={}", joinPoint.getArgs()); //전달 인자
        log.info("getSignature={}", joinPoint.getSignature()); //joinPoint 시그니쳐

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //로직 호출(target 호출)
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
