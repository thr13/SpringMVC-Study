package com.advanced.aop.pointcut;

import com.advanced.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    void typeExactMatch() {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member.MemberServiceImpl 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member.MemberService 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member.MemberService.*(..))"); //부모 타입으로 선언해도 자식 타입은 매칭이 가능하다
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member.MemberServiceImpl 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        //접근제어자: (생략) 반환타입: * 선언타입: com.advanced.member.MemberService 메서드이름: * 파라미터: (..) 예외: (없음)
        pointcut.setExpression("execution(* com.advanced.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class); //부모타입인 MemberService 에는 internal 이라는 메서드가 없다!!
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse(); //타입 매칭은 부모 타입에 있는 메서드만 허용된다!!
    }

    @DisplayName("파라미터 없는 것을 매칭") //()
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @DisplayName("정확히 하나의 파라미터, 모든 타입 허용") //(Xxx)
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @DisplayName("숫자와 무관하게 모든 파라미터, 타입 허용") //(), (Xxx), (Xxx, Xxx)
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @DisplayName("String 타입으로 시작하고 숫자와 무관한 모든 파라미터, 타입 허용") //(String), (String, Xxx), (String, Xxx, Xxx)
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


}
