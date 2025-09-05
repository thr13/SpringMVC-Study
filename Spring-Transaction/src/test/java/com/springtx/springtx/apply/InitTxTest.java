package com.springtx.springtx.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
class InitTxTest {

    @Autowired
    Hello hello;

    @Test
    void go() {
        // 초기화 코드: 스프링이 초기화 시점에 호출
        hello.initV2();
    }

    @TestConfiguration
    static class InitTxTestConfig {

        @Bean
        Hello hello() {
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {

        @PostConstruct // 스프링 컨테이너가 스프링 빈을 등록하고 나서 초기화 메소드를 호출
        @Transactional // 주의: @PostConstruct 와 같은 초기화 코드와 @Transactional 을 함께 사용하면 트랜잭션이 적용되지 않는다!! -> 초기화 코드가 먼저 호출되고나서 트랜잭션 AOP 가 적용되기 때문
        public void initV1() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @PostConstruct tx active={}", isActive);
        }

        @EventListener(ApplicationReadyEvent.class) // 트랜잭션 AOP 포함한 스프링이 컨테이너가 완전히 생성되고 난 뒤, @EventListener 가 붙은 메서드를 호출
        @Transactional
        public void initV2() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @ApplicationReadyEvent tx active={}", isActive);
        }
    }


}
