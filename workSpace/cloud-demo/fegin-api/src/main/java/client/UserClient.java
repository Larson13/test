package client;


import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pojo.R;
import pojo.User;

@FeignClient("userservice")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);

    @PostMapping(value = "/user/register",produces = "application/json;charset=utf-8")
     R<String> register(@RequestBody User user);

    @PostMapping("/user/query")
     User queryById(@RequestParam("username") String username );
}
