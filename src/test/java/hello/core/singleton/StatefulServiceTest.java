package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatefulService.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자 A 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB: 사용자 B 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문금액 조회 - 10000원이 출력하기를 기대함
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 실제 출력은 20000원 -> 같은 인스턴스를 사용하기 때문에 사용자 B 가 인스턴스의 상태를 변경시킴

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}