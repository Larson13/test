package cn.com.perf.beihe.pinter.servlet;

import cn.com.perf.beihe.pinter.dao.UserMapper;
import cn.com.perf.beihe.pinter.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert(User paramUser){userMapper.insert(paramUser);}

}
