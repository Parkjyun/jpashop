package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "hello!!!");
        return "hello";//return의 헬로는 화면의 이름 return hello.html임

    }
}
