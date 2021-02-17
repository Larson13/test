package com.reggie.config;


import com.alibaba.fastjson.JSON;
import com.reggie.common.BaseContext;
import com.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //A. 获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}", requestURI);
        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**"

         };

        //B. 判断本次请求, 是否需要登录, 才可以访问
         boolean check = check(requestURI,urls);
        //C. 如果不需要，则直接放行
         if(check){
             log.info("本次请求{}不需要处理",requestURI);

             filterChain.doFilter(request,response);
             return;
         }
        //D. 判断登录状态，如果已登录，则直接放行
        Object employee = request.getSession().getAttribute("employee");
         if(employee !=null){
             log.info("用户已登录，用户id为： {}",employee);
             BaseContext.setCurrentId((Long) employee);
             filterChain.doFilter(request,response);
             return;
         }

        //E. 如果未登录, 则返回未登录结果
        log.info("用户未登录");

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }
    public boolean check(String requestURI,String[] urls){
        for (String url:urls) {

            boolean match = PATH_MATCHER.match(url,requestURI);
            if(match){
                return true;
            }
        }
        return  false;


    }
}
