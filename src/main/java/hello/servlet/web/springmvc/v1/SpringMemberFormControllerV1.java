package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
@Controller: 스프링이 자동으로 스프링 빈으로 등록함, 스프링 MVC 이 애노테이션 기반 컨트롤러로 인식함
또한, RequestMappingHandlerMapping 이 핸들러 정보임을 인식하고 꺼냄
(즉, 컴포넌트 스캔 대상으로 만들어주고, RequestMappingHandlerMapping 에서 사용됨)
 */
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}