package cn.testfan.myspringboot.service;

import cn.testfan.myspringboot.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void select() {
        User user = userService.select(20);
        Assertions.assertEquals(user.getAge(), 24);
    }

    @Test
    void update() {
        User user = new User();
        user.setId(20);
        user.setUserName("xxx");
        user.setAge(99);
        user.setPassword("1234");
        int row = userService.update(user);
        Assertions.assertEquals(row, 2);
    }

}