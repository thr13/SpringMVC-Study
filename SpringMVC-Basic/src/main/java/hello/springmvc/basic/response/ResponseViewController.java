package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    // @ResponseBody 사용시 리턴한 문자가 그대로 메시지 바디로 들어가므로 뷰를 찾지 않는다!! (@RestController 마찬가지)
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");

        return "response/hello"; // View 의 논리적 이름 반환(상대경로) -> ViewResolver 가 실행되어 해당 뷰를 물리적 이름으로 변환한다
    }

    @RequestMapping("/response/hello") // 컨트롤러의 경로 이름과 뷰의 논리적 이름이 똑같으면 따로 리턴안해도 경로가 논리적 뷰의 이름으로 진행된다
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }

}