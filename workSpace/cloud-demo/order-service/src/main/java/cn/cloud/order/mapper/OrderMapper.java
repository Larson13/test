package cn.cloud.order.mapper;

import cn.cloud.order.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface OrderMapper {

    @Select("select * from  tb_order where id = #{id}")
    Order findById(@Param("id") Long id);

    /**
     *     private Long id;
     *     private Long price;
     *     private String name;
     *     private Integer num;
     *     private Long userId;
     *     private User user;
     *
     *     "insert into tb_user(username,address) values(#{username},#{address})"
     * @param order
     */
    @Insert("insert into tb_order(name,price,user_id) values(#{name},#{price},#{userId})")
    void add(Order order);
}
