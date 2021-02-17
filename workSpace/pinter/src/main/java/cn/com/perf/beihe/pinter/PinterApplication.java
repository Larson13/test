package cn.com.perf.beihe.pinter;

import cn.com.perf.beihe.pinter.servlet.UserServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
@MapperScan({"cn.com.perf.beihe.pinter.dao"})
public class PinterApplication {
    public static void main(String[] args) {
        SpringApplication.run(cn.com.perf.beihe.pinter.PinterApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean myServlet1() {
        return new ServletRegistrationBean((Servlet)new UserServlet(), new String[] { "/case/memory" });
    }
}
