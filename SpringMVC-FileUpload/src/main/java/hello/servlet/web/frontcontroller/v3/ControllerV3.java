package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * ModelView 객체를 직접 만들었기 때문에, 프레임워크에 종속적이고 서블릿에 종속적이지 않도록 설정됨
 */
public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}