package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청 정보 받기
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 비즈니스 로직을 컨트롤러(Servlet)가 호출
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        // Model 에 데이터를 보관
        request.setAttribute("member", member); // request 객체 내부의 Map 에 저장
        String viewPath = "/WEB-INF/views/save-result.jsp"; // 외부에서 접근불가능한 WEB-INF 내 뷰 호출
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
