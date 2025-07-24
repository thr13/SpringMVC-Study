package hello.typeconverter.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); // 문자열 타입
        Integer intValue = Integer.valueOf(data); // 문자열을 정수 타입(int)으로 변경
        System.out.println("intValue = " + intValue);

        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) { // @RequestParam 으로 받는 문자는 스프링이 중간에서 정수 타입으로 변환해준다!!
        System.out.println("data = " + data);

        return "ok";
    }
}
