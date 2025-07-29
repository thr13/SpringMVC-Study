package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); // GET - HTTP 메서드
        System.out.println("request.getProtocol() = " + request.getProtocol()); // HTTP/1.1 - HTTP 프로토콜
        System.out.println("request.getScheme() = " + request.getScheme()); // http - scheme(스키마)
        System.out.println("request.getRequestURL() = " + request.getRequestURL());// http://localhost:8080/request-header - URL
        System.out.println("request.getRequestURI() = " + request.getRequestURI());// /request-header - URI(해당 경로의 끝부분)
        System.out.println("request.getQueryString() = " + request.getQueryString()); // username=hi - ? 뒤에 나오는 쿼리 파라미터
        System.out.println("request.isSecure() = " + request.isSecure()); // https 사용유무 - HTTPS 로 시작할 경우 True, HTTP 인 경우 False
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    // Header 의 모든 정보를 가져오는 방식
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        /*
        Enumeration<String> headerNames = request.getHeaderNames(); // 옛날방식 - HTTP 요청 메시지를 Enumeration<> 타입으로 반환받아 사용
        while (headerNames.hasMoreElements()) { // 요소가 있는 경우 반복 실행
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + headerName);
        }
         */

        // 위 코드를 간결하게 사용하기
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + headerName));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales()
                .asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale()); // Accept-Language 우선순위 중 가장 높은것을 자동으로 출력함
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType()); // GET 은 body 를 보낼일이 거의 없으므로 null 값 반환
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    //기타 정보 - HTTP 메시지 정보가 아닌 정보, 내부에서 네트워크 커넥션이 맺어진 정보들
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]"); // HTTP 요청이 온 것에 대한 정보
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]"); // 현재 내 서버 정보
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
