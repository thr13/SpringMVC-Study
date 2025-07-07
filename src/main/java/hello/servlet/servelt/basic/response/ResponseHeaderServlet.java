package hello.servlet.servelt.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); // 200 를 의미한는 HttpServletResponse 객체가 지원하는 상수 SC_OK
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8"); // HTTP 응답 메시지의 Content-Type
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // Cache-Control 로 캐시에 대한 내용, no-cache, no-store, must-revalidate 은 캐시를 완전히 무효화 하겠다고 선언
        response.setHeader("Pragma", "no-cache"); // 헤더, 캐시 무효화 - 과거 버전까지 다 캐시를 없앰
        response.setHeader("my-header", "hello"); // 커스텀 헤더 - HTTP 응답 헤더에 포함됨

        // header 편의 메서드
//        content(response);
//        cookie(response);
        redirect(response);

        // message-body
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good"); // 위 코드와 같은 의미
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
