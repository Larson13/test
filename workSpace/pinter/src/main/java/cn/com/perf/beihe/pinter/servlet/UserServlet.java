package cn.com.perf.beihe.pinter.servlet;

import cn.com.perf.beihe.pinter.mode.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            response.getWriter().write("param is null");
            response.flushBuffer();
            return;
        }
        long randomTime = (long)(Math.random() * 5.0D);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        HttpSession session = request.getSession();
        if (session.getAttribute("users") == null) {
            List<User> list1 = new ArrayList<>();
            request.getSession().setAttribute("users", list1);
        }
        List<User> list = (List<User>)session.getAttribute("users");
        list.add(user);
        try {
            Thread.currentThread();
            Thread.sleep(randomTime + 5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write("{\"code\":\"0\"}");
        response.flushBuffer();
    }
}