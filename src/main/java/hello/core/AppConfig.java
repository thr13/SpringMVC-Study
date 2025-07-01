package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
애플리케이션 전체를 설정하고 구성하는 클래스(환경설정 담당)
각 서비스 계층에서 구현체를 직접 할당하는게 아닌, AppConfig 가 대신 구현체를 생성해주고 이를 서비스계층에서 주입시켜, 의존관계를 변경함
즉, 서비스 계층 구현체의 생성자 주입을 통해서, 리파지토리 구현체를 생성함

추가: 스프링 컨테이너는 기본적으로 객체를 싱글톤으로 만들어서 관리해 줌
 */

public class AppConfig {

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() { // DB 를 변경할 경우 이 메소드만 수정하면 된다
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() { // 할인 정책이 변경될 경우 이 메소드만 수정하면 된다
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
