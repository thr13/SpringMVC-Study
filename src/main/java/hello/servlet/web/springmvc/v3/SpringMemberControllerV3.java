package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ViewName 을 String 으로 직접 반환
 * HttpServletRequest, HttpServletResponse 대신 @RequestParam 사용
 * GET, POST 명시
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() { // 문자로 반환시, 스프링이 매핑된 View 이름을 자동으로 찾아줌
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) { // 타입캐스팅이 자동으로 처리됨

        Member members = new Member(username, age);
        memberRepository.save(members);

        model.addAttribute("member", members);

        return "save-result";
    }

    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
