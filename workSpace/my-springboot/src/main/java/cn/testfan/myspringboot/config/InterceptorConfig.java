package cn.testfan.myspringboot.config;

import cn.testfan.myspringboot.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;


    /**
     * addPathPatterns为 /**,下面的exclude才起作用，且不管controller层是否有匹配客户端请求，拦截器都起作用拦截
     * addPathPatterns("/hello") 如果add为具体的匹配如"/hello"，下面的exclude不起作用,且controller层不匹配客户端请求时拦截器不起作用
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/**")
                //排除不需要验证登录用户操作权限的请求
                .excludePathPatterns("/demo/login")
        .excludePathPatterns("/demo/add");
    }
}
