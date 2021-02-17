package cn.com.perf.beihe.pinter.http;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.perf.beihe.pinter.http.base.BaseController;
import cn.com.perf.beihe.pinter.mode.RestResponse;
import cn.com.perf.beihe.pinter.utils.CommonUtils;
import cn.com.perf.beihe.pinter.utils.TokenCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/bank"})
public class BankController extends BaseController {
    @GetMapping({"/page/login"})
    public String toLogin(Model model) {
        model.addAttribute("desc", "by <a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "login";
    }

    @GetMapping({"/page/login2"})
    public String toLogin2(Model model) {
        model.addAttribute("desc", "by <a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "login2";
    }

    @GetMapping({"/page/welcome"})
    public String toWelcome(Model model) {
        model.addAttribute("desc", "by <a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "welcome";
    }

    @GetMapping({"/page/welcome2"})
    public String toWelcome2(HttpServletRequest request, String userName, Model model) {
        if (StringUtils.isBlank(userName))
            return "login2";
        ServletContext context = request.getServletContext();
        if (context.getAttribute(userName + "-token") == null)
            return "login2";
        model.addAttribute("token", context.getAttribute(userName + "-token"));
        model.addAttribute("desc", "by <a style='color:#337ab7' href='http://www.baidu.cn></a>@树能");
        return "welcome2";
    }

    @GetMapping({"/page/money"})
    public String toMoney(Model model) {
        model.addAttribute("desc", "by <a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "money";
    }

    @GetMapping({"/page/money2"})
    public String toMoney2(HttpServletRequest request, String userName, Model model) {
        if (StringUtils.isBlank(userName))
            return "login2";
        ServletContext context = request.getServletContext();
        if (context.getAttribute(userName + "-token") == null)
            return "login2";
        model.addAttribute("token", context.getAttribute(userName + "-token"));
        model.addAttribute("desc", "by <a style='color:#337ab7' href='http://www.baidu.c'></a>@树能");
        return "money2";
    }

    @PostMapping({"/api/login"})
    @ResponseBody
    public RestResponse<Object> login(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        if (StringUtils.isBlank(userName) ||
                StringUtils.isBlank(password))
            return wrap("1", "success");
                    ServletContext context = request.getServletContext();
        String userCookie = UUID.randomUUID().toString();
        context.setAttribute(userName + "-cookie", userCookie);
        Cookie cookie = new Cookie("testfan-id", userCookie);
        cookie.setMaxAge(216000);
        cookie.setPath("/");
        response.addCookie(cookie);
        return wrap("0", "success");
    }

    @PostMapping({"/api/login2"})
    @ResponseBody
    public RestResponse<Object> login2(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        if (StringUtils.isBlank(userName) ||
                StringUtils.isBlank(password))
            return wrap("1",  "参数为空");
                    ServletContext context = request.getServletContext();
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        context.setAttribute(userName + "-token", token);
        TokenCache.INSTANCE.put(userName, token);
        return wrap(token);
    }

    @GetMapping({"/api/query"})
    @ResponseBody
    public RestResponse<Object> query(HttpServletRequest request, String userName) {
        if (StringUtils.isBlank(userName))
            return wrap("1", "参数为空");
                    ServletContext context = request.getServletContext();
        String serverCookie = (String)context.getAttribute(userName + "-cookie");
        if (StringUtils.isBlank(serverCookie))
            return wrap("1", "用户未登陆");
                    Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("testfan-id") && serverCookie.equals(cookie.getValue()))
                    return wrap(CommonUtils.generateRandomMoney());
            }
        return wrap("1", "用户不合法");
    }

    @GetMapping({"/api/query2"})
    @ResponseBody
    public RestResponse<Object> query2(HttpServletRequest request, String userName) {
        if (StringUtils.isBlank(userName))
            return wrap("1", "参数为空");
                    String userToken = request.getHeader("testfan-token");
        ServletContext context = request.getServletContext();
        String serverToken = (String)context.getAttribute(userName + "-token");
        if (StringUtils.isBlank(serverToken))
            return wrap("1",  "用户未登陆");
        if (serverToken.equals(userToken))
            return wrap(CommonUtils.generateRandomMoney());
        return wrap("1", "用户不合法");
    }

    @GetMapping({"/api/logout"})
    @ResponseBody
    public RestResponse<Object> logout(HttpServletRequest request, String userName) {
        if (StringUtils.isBlank(userName))
            return wrap("1", "参数为空");
                    ServletContext context = request.getServletContext();
        context.removeAttribute(userName + "-token");
        context.removeAttribute(userName + "-cookie");
        HttpSession session = request.getSession();
        session.invalidate();
        return wrap("0", "success");
    }
}
