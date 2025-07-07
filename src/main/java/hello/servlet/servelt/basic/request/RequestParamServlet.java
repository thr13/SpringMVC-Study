package hello.servlet.servelt.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 긴능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "RequestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Enumeration<String> parameterNames = request.getParameterNames();// HTTP 요청의 모든 파라미터 데이터가 Enumeration<> 객체에 담겨있다
        System.out.println("[전체 파라미터 조회] - start");

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(
                        paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username"); // 특정 파라미터 Key 를 String[] 타입으로 받아올 수 있다
        for (String name : usernames) {
            System.out.println("username = " + name); // 같은 이름의 파라미터 username 의 값을 둘다 조회할 수 있다!
        }

        response.getWriter().write("ok");
    }
}
