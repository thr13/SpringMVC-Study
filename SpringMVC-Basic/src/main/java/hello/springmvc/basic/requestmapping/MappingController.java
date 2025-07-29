package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    // private Logger log = LoggerFactory.getLogger(getColass());

    @RequestMapping(value = "/hello-basic") // 해당 URL 이 오면 결과값으로 HTTP 메시지 바디로 응답을 한다
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * @RequestMapping 의 method 설정이 없다면 요청 구분을 하지 않는다
     * 주의: 스프링 부트 3.0 부터는 URL 경로의 마지막의 /(slash)를 유지 하므로 /hello-basic 와 /hello-basic/ 이 같다고 판단한다
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * @GetMapping 내부을 확인하면 @RequestMapping(method = RequestMethod.GET) 로 되어있다
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable(경로 변수) 사용
     * 변수명이 같으면 생략 가능: @PathVariable 의 매개변수인 ("userId") 와 뒤에 오는 String 타입 변수 userId 가 같다면 @PathVariable 매개변수 생략가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable(다중매핑)
     * String userId 는 경로의 {userId} 에 Long orderId 는 경로의 orderId 에 들어감
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터로 추가 매핑: 경로의 ? 뒤에 파라미터로 mode=debug 가 없으면 출력이 되지 않는다
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑: HTTP 요청을 보낼때, header 에 mode=debug 라는 값이 있어야지 보낼 수 있다
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * 이 경우 header 를 사용하면 안되고 consume 을 사용해야 한다(스프링 mvc 에서 내부적으로 consume 을 가지고 처리하기 때문)
     * header 의 Content-Type 이 application/json 인 경우에만 호출됨
     * consumes 은 서버 입장에서 소비하는 것, 소비하는 입장에서 보면 요청으 content-type 에 정보를 소비함, produce 는 컨트롤러가 생산해내는 Content-type 이고 이때는 요청이 Accept 가 되야한다
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce**
     * Accept 헤더 기반 Media Type
     * header 의 Accept 가 필수이다
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}