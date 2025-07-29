package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * 프론트 컨트롤러가 모델까지 만들여서 넘겨줌
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
