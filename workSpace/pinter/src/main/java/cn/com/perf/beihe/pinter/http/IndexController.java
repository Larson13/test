package cn.com.perf.beihe.pinter.http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping
    @RequestMapping({"/index"})
    public String index(Model model) {
        model.addAttribute("desc", "<a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "index";
    }

    @GetMapping
    @RequestMapping({"/doc"})
    public String doc(Model model) {
        model.addAttribute("desc", "M<a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "doc";
    }
}
