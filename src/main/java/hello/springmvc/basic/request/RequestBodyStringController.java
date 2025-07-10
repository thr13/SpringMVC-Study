package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /**
     * HttpEntity: HTTP header 와 body 정보를 편하게 조회할 수 있다
     * 메시지 바디 정보를 직접 조회 가능하다
     * 단, HTTP 요청 파라미터를 조회하는 기능과 관계가 없다(@RequestParam, @ModelAttribute)
     * 또한, HttpEntity 는 HTTP 응답에 사용 가능함(헤더 정보를 포함할 수 있으나 View 가 조회할 수 없다)
     * HttpEntity 를 상속받는 RequestEntity, ResponseEntity 가 있다
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        //return new HttpEntity<>("ok"); // HttpEntity 매개변수로 메시지 바디에 들어갈 내용을 넣을 수 있다
        return new ResponseEntity<String>("ok", HttpStatus.CREATED); // HTTP 메시지 바디 메세지와 HTTP 상태 코드를 전달할 수 있다
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
