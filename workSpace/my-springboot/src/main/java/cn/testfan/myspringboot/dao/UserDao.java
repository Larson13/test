package cn.testfan.myspringboot.dao;

import cn.testfan.myspringboot.model.User;

public interface UserDao {

    int insert(User user);

    User select(Integer id);

    int update(User user);

    int delete(Integer id);

}
