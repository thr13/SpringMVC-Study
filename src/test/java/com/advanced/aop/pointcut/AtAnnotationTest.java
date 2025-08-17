package com.advanced.aop.pointcut;

import com.advanced.member.MemberService;
import com.advanced.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * @annotation: 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트를 매칭
 * @args: 전달된 실제 인수의 런타입 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
 */
@Slf4j
@Import(AtAnnotationTest.AtAnnotationAspect.class)
@SpringBootTest(classes = MemberServiceImpl.class)
public class AtAnnotationTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
    }

    @Slf4j
    @Aspect
    static class AtAnnotationAspect {

        @Around("@annotation(com.advanced.member.annotation.MethodAop)")
        public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
