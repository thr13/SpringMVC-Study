package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

/*
url 경로로 hello 가 올 경우 아래 클래스가 실행된다
주의: 서블릿의 name 과 urlPatterns 의 매핑이름은 중복되면 안된다
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet { // Servlet 은 HTTPServlet 클래스를 상속 받는다!!


    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException { // WAS 내부의 Servlet 이 자동으로 HTTPRequest, HTTPResponse 객체를 생성해준다

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 요청 메시지 - 쿼리 파라미터
        String username = request.getParameter("username"); // ServletRequest 의 getParameter() 메서드로 특정 쿼리 파라미터를 조회할 수 있다
        System.out.println("username = " + username);

        // 응답 메시지 - ContentType 즉, HTTP 헤더 정보로 들어감
        response.setContentType("text/plain"); // 단순 문자인 경우 text/plain
        response.setCharacterEncoding("utf-8"); // 인코딩 정보

        // 응답 메시지 - HTTP Message Body
        response.getWriter().write("hello " + username); // ServletResponse 의 write() 메서드로 HTTP message body 에 데이터를 삽입할 수 있다
    }
}