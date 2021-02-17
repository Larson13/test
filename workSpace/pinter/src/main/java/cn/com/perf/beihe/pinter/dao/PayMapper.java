package cn.com.perf.beihe.pinter.dao;

import cn.com.perf.beihe.pinter.mode.Order;

public interface PayMapper {
    Order selectByPrimaryKey(Integer paramInteger);

    void insert(Order paramOrder);

    void deleteByPrimaryKey(Integer paramInteger);
}
