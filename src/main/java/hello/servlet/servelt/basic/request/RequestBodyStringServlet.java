package hello.servlet.servelt.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // getInputStream 메서드로 HTTP Message Body 의 바이트 코드를 얻을 수 있다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 스프링이 제공하는 유틸리티 클래스, byte 를 문자형으로 변경할떄 인코딩 정보를 알려줘야한다

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
