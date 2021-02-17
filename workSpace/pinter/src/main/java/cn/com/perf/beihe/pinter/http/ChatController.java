package cn.com.perf.beihe.pinter.http;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/chat"})
public class ChatController {
    @GetMapping
    @RequestMapping({"/page/toChat"})
    public String chat(Model model) {
        model.addAttribute("desc", "Made by <a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "chat";
    }
}
