package cn.com.perf.beihe.pinter.dao;

import cn.com.perf.beihe.pinter.mode.User;

public interface UserMapper {
    User selectByPrimaryKey(Integer paramInteger);

    User selectByUserName(String paramString);

    User selectByUserNameAndEmail(User paramUser);

    void insert(User paramUser);

    void updateByPrimaryKey(User paramUser);

    void deleteByPrimaryKey(Integer paramInteger);
}
