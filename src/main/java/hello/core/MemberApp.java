package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
        스프링은 애플리케이션 컨텍스트 에서 시작한다
        즉, ApplicationContext 를 스프링 컨테이너라고 볼 수 있다

        AnnotationConfigApplicationContext 의 매개변수로 AppConfig.class 를 넣으면, AppConfig.class 에 있는 환경 설정 정보를 가지고
        스프링이 자동으로 스프링 빈을 스프링 컨테이너에 집어넣어서 관리해준다
        해당 스프링 빈을 사용하긴 위해선 ApplicationContext 객체의 getBean(등록된메서드이름, 클래스파일.class) 메소드를 사용해야한다
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
