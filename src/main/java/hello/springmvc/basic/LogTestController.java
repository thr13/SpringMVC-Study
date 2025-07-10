package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@Controller: API 반환값이 String 이면 뷰 이름으로 인식한다 그래서 ViewResolver 가 View 를 찾고 해당 뷰가 Rendering 된다
@RestController: RestController(REST API 의 약자)는 반환값으로 뷰를 찾는게 아니라, HTTP 메시지 바디에 바로 입력하므로 실행 결과인 문자열이 그대로 출력된다
 */
@RestController
@Slf4j
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); // Lombok 의 @Slf4j 로 대체 가능

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name); // print.out 은 전부 출력되므로 로깅으로 로그 레벨을 구분해야된다

        // 로깅 레벨
        log.trace("trace log={}", name); // 로컬 서버 에서 로그를 전부 확인할때 사용됨
        log.debug("debug log={}", name); // 개발 서버 등에서 사용됨
        log.info("info log={}", name); // 비즈니스 정보, 운영 시스템 정보등 출력(실제 서비스 되는 운영 서버에서 사용), 디폴트 로깅 레벨 info(info, warn, error 출력가능)
        log.warn("warn log={}", name);
        log.error("error log={}", name); // 에러로그, 알람 또는 별도 파일 보관

        /*
        // 아래의 경우 메서드 호출 전, 문자 연산(+)가 발생되므로 로그 레벨과 관계없이 더하기 연산(쓸모없는 리소스)를 사용하게 된다(물론 로깅 레벨설정에 의해 출력은 되지 않음)
        log.debug("trace my log=" + name);
         */

        return "ok";
    }
}
