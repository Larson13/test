package cn.cloud.user.service;

import cn.cloud.user.mapper.UserMapper;
import cn.cloud.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.findById(id);
    }

    public void insert(User user) {
        userMapper.insert(user);

    }


    public User queryByUsername(String username) {

        return userMapper.queryByUsername(username);
    }
}