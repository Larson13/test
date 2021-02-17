package cn.testfan.myspringboot.service.impl;

import cn.testfan.myspringboot.dao.UserDao;
import cn.testfan.myspringboot.model.User;
import cn.testfan.myspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User select(Integer id) {
        User user = userDao.select(id);
        return user;
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }
}
