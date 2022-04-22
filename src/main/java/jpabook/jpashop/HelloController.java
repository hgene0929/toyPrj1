package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // URL 매핑
    public String hello(Model model){ // model은 데이터를 담아서 옮길 객체
        model.addAttribute("data", "hello!!!");
        return "hello"; // 화면이름을 반환
    }
}
