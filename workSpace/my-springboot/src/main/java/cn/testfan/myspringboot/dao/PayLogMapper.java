package cn.testfan.myspringboot.dao;

import cn.testfan.myspringboot.model.PayLog;

public interface PayLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);
}