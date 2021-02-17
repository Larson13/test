package cn.testfan.myspringboot.service;

import cn.testfan.myspringboot.model.User;

public interface UserService {

    int insert(User user);

    User select(Integer id);

    int update(User user);

    int delete(Integer id);


}
