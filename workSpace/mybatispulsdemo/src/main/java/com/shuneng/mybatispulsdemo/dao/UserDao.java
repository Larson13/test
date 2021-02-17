package com.shuneng.mybatispulsdemo.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.shuneng.mybatispulsdemo.model.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
