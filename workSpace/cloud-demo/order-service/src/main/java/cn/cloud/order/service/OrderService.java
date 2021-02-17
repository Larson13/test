package cn.cloud.order.service;

import cn.cloud.order.pojo.Order;
import cn.cloud.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 4.返回
        return order;
    }

    public void add(Order order) {
        orderMapper.add(order);
    }
}
