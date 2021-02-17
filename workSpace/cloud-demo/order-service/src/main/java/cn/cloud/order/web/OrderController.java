package cn.cloud.order.web;


import client.UserClient;
import cn.cloud.order.pojo.Order;
import cn.cloud.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pojo.R;
import pojo.User;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

//   @Autowired
//   private RestTemplate restTemplate;
//   @Value("${pattern.dateformat}")
//   private String dateformat;
   @Autowired
   private UserClient userClient;
//    @GetMapping("/now")
//    public String now(){
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
//    }
    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        log.info("orderId: {}",orderId);
        Order order = orderService.queryOrderById(orderId);
        log.info("userid: {}",order.getUserId());
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        log.info("user: {}",user);
        return order;
    }
    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    public R<String> add(@RequestBody Order order){
        log.info("接收订单: {}",order);
        //查用户
       User user=userClient.queryById(order.getUser().getUsername());
        log.info("查到user：{}",user);
        //判断用户是否存在，不存在创建一个用户
        if(user ==null){
            User user1 =order.getUser();
            log.info("准备创建用户：${}",user1);
            userClient.register(user1);
            //查用户id
            user = userClient.queryById(user1.getUsername());
            log.info("用户不存在，创建用户: {}",user);
        }
        order.setUserId(user.getId());
        //创建订单
        log.info("新增订单: {}",order);
        orderService.add(order);
        return R.success("下单成功");
    }

}
