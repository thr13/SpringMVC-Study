package com.advanced.proxyvs.code;

import com.advanced.member.MemberService;
import com.advanced.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //스프링이 AOP 프록시를 생성할떄 JDK 동적 프록시를 우선 생성하도록 설정(인터페이스가 없다면 GGLIB 사용)
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 프록시 생성
@Import(ProxyDIAspect.class)
public class ProxyDiTest {

    @Autowired
    MemberService memberService;

    /**
     * JDK 동적 프록시는 구현체를 모르기 때문에 의존관계 주입 불가
     * 그러나 CGLIB 는 구현 클래스를 기반으로 프록시가 생성되기 때문에 타입 캐스팅 가능
     */
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void goJDKProxy() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        //예상된 주입은 memberServiceImpl 이지만 실제 넘어온 타입은 프록시 이기 때문에 예외 발생
        Assertions.assertThrows(UnsatisfiedDependencyException.class,
                () -> memberServiceImpl.hello("hello")
        );
    }

    @Test
    void goCGLIB() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }
}
