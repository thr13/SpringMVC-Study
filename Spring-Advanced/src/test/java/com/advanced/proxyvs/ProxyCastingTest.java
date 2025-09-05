package com.advanced.proxyvs;

import com.advanced.member.MemberService;
import com.advanced.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK 동적 프록시는 대상 객체(인터페이스)의 구현체로 캐스팅할 수 없다!!
 * CGLIB 는 구체 클래스를 기반으로 프록시를 생성하므로 캐스팅할 수 있다
 */
@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //인터페이스 기반인 JDK 동적 프록시

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy(); //MemberService 인터페이스 기반으로 JDK 프록시 생성
        log.info("proxy class={}", memberServiceProxy.getClass());
        //JDK 동적 프록시는 인터페이스를 구현하므로 구체 타입으로 캐스팅 불가능
        assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); //CGLIB 프록시

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        log.info("proxy class={}", memberServiceProxy.getClass());
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy; //CGLIB 프록시를 구현 클래스로 캐스팅
    }
}
