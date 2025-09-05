package com.advanced.cglib;

import com.advanced.cglib.code.TimeMethodInterceptor;
import com.advanced.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); //CGLIB 제작
        enhancer.setSuperclass(ConcreteService.class); //구체 클래스를 상속 받아서 프록시를 만든다
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create(); //프록시 생성
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass()); //대상클래스$$EnhancerByCGLIB$$임의코드

        proxy.call();
    }
}
