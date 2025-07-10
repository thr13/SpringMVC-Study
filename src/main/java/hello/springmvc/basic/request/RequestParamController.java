package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // HttpServletRequest 가 제공하는 GET 파라미터
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok"); // 반환타입이 void 여도 response 에 값을 쓰면 메시지 바디에 그대로 들어간다
    }

    @ResponseBody // @RestController 대신 사용 -> 반환되는 문자열을 View 를 찾는게 사용하지 않고 곧바로 HTTP 메세지 바디에 값을 넣는다 -> 즉, @RestController 와 같은 효과이다
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName, // HTTP 요청의 이름("username")
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok"; // ViewResolver 가 "ok" 라는 논리적인 이름을 가진 View 를 찾는다 -> 단순히 "ok" 라는 문자를 http 메시지 바디에 넣을려면 @Controller 가 아닌 @RestController 를 사용해야한다
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 변수명과 요청파라미터 이름이 같을 경우 파라미터 생략가능
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // 단순 타입인 경우 @RequestParam 도 생략가능하다!!

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username // required 옵션이 true(디폴트값) 인 경우 해당 파라미터는 무조건 있어야 한다!!
            ,@RequestParam(required = false) int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam: defaultValue 옵션 사용
     * 참고: defaultValue 는 빈 문자("")의 경우에도 설정한 기본값이 적용
     * /request-param-default?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, // required 가 true 임에도 불구하고, 없으면 defaultValue 값이 들어간다
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * 요청 파라미터를 Map 으로 조회하기
     * @RequestParam Map(하나의 키에 하나의 값), MultiValueMap(하나의 키에 여러 값이 들어감)
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));

        return "ok";
    }
}
