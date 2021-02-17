package cn.cloud.order;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("cn.cloud.order.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "client")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    public  RestTemplate restTemplate(){
//        return new RestTemplate();
  // }

//    @Bean
//    public IRule randomRule(){return new RandomRule();}


}
