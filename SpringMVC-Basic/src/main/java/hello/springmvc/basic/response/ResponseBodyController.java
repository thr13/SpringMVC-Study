package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

// @RestController == REST API 를 만들때 사용함
@Slf4j
@Controller// @RestController 또는 @ResponseBody 를 클래스 레벨에 붙여서 메소드 레벨에서 애노테이션 하나를 뺼 수 있다
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok"); // HTTP 메시지 바디에 직접 응답 메시지 전달
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK); // 상태코드를 직접 삽입 가능
    }

    @ResponseBody // @ResponseBody 사용시 View 를 사용하지 않고, HTTP 메시지 컨버터를 통해 HTTP 메시지를 직접 입력할 수 있다
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @ResponseBody
    @GetMapping("/response-body-json-v1")
    public HelloData responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData; // HTTP 메시지 컨버터를 통해 JSON 으로 변환된다
    }

    @ResponseStatus(HttpStatus.OK) // @ResponseStatus 로 상태코드를 넣어줄 수 있다
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}