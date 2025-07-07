package hello.servlet.servelt.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.servelt.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json
        response.setContentType("application/json"); // HTTP 응답으로 JSON 변ㄴ환시 Content-Type 을 application/json 으로 지정해야한다
        response.setCharacterEncoding("utf-8"); // JSON 은 스펙상 utf-8 같은 추가 파라미터를 지원하지 않는다(사실상 의미 없는 코드란 의미)

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //{"username":"kim", "age":20}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
