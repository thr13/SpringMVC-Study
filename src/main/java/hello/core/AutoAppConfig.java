package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정 정보 이므로 @Configuration 등록
@ComponentScan( // @Component 이 붙은 클래스를 모두 찾아서 자동으로 스프링 빈에 등록함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // @Configuration 을 사용하는 AppConfig.class 을 컴포넌트 스캔 대상에서 제외하여 자동 등록을 방지함
)
public class AutoAppConfig {

}
