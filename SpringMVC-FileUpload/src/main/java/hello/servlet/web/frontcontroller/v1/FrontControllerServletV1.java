package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // /front-controller/v1 을 포함한 하위 경로의 모든 요청은 이 서블릿에서 받아들인다
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> contollerMap = new HashMap<>(); // Key 는 요청 URL, Value 는 호출된 컨트롤러 객체

    public FrontControllerServletV1() { // /front-controller/v1/* 경로를 실행하면 각각의 컨트롤러를 생성함
        contollerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        contollerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        contollerMap.put("/front-controller/v1/members/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members 와 같음
        String requestURI = request.getRequestURI();

        ControllerV1 controller = contollerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        controller.process(request, response);
    }
}
