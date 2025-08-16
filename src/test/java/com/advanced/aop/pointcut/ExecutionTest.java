package com.advanced.aop.pointcut;

import com.advanced.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

/**
 * execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
 * ? 가 붙은 것들은 생략 가능 즉, 최소한 반환타입 메서드이름(파라미터) 는 입력해야한다
 * . 정확하게 해당 위치의 패키지
 * .. 해당 위치의 패키지와 그 하위 패키지 포함
 */
@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(); //AspectJ 포인트컷 표현식 처리 클래스(상위에 Pointcut 인터페이스 존재)
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        log.info("helloMethod={}", helloMethod); //public java.lang.String com.advanced.member.MemberServiceImpl.hello(java.lang.String) 출력
    }

    @Test
    void exactMatch() {
        //접근제어자: public 반환타입: String 선언타입: com.advanced.member.MemberServiceImpl 메서드이름: hello 파라미터: (String) 예외: (생략)
        pointcut.setExpression("execution(public String com.advanced.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        //접근제어자: (생략) 반환타입: * 선언타입: (생략) 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        //접근제어자: (생략) 반환타입: * 선언타입: (생략) 메서드이름: hello 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch1() {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member.MemberServiceImpl 메서드이름: hello 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member.* 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactFalse() {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.* 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.*..*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactSubPackage1() {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member..* 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
