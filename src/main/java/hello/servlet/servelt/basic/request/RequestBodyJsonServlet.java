package hello.servlet.servelt.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.servelt.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper(); // JSON 결과를 파싱해서 자바 객체로 변환하기 위한 JackSon 라이브러리의 ObjectMapper 클래스(Spring MVC 기본제공)

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // HTTP Message Body 를 읽음
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 읽어온 byte 데이터를 String 으로 변환

        System.out.println("messageBody: " + messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);// 해당 클래스 타입으로 JSON 데이터가 자동 변환됨

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.getAge = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
