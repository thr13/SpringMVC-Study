package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정 정보 이므로 @Configuration 등록
@ComponentScan( // @Component 이 붙은 클래스를 모두 찾아서 자동으로 스프링 빈에 등록함
        basePackages = "hello.core.member", // 컴포넌트 스캔 경로를 hello.core.member 패키지로 제한함
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // @Configuration 을 사용하는 AppConfig.class 을 컴포넌트 스캔 대상에서 제외하여 자동 등록을 방지함
) // 탐색 위치와 기본 스캔 대상을 지정하지 않을경우, @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
public class AutoAppConfig {

    /*
    @Bean(name = "memoryMemberRepository") //  수동 빈 등록시 스프링 부트가 자동 빈 등록과 충돌시킨다
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */
}
