package com.advanced.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 리플렉션
 */
@Slf4j
public class ReflectionTest {

    @Test
    void reflection() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); //위와 로직은 같으나 호출하는 메서드가 다름 -> 중간에 호출하는 메서드 부분이 달라서 메서드로 뽑아서 공통화하는게 어려움 -> 호출 메서드만 동적으로 처리한다면 이 문제를 해결할 수 있음
        log.info("result={}", result2);
        //공통 로직2 종료

    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "A";
        }
    }

    @Test
    void reflection1() throws Exception {
        //리플렉션으로 클래스 메타정보 획득 -> 내부 클래스는 $ 기호를 사용해서 꺼낼 수 있다
        Class classHello = Class.forName("com.advanced.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        //callA 메서드 메타정보 획득
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //callB 메서드 메타정보 획득
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target); //실제 인스턴스 메서드 호출
        log.info("result={}", result);
    }
}
