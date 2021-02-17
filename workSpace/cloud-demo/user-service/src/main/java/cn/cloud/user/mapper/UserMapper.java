package cn.cloud.user.mapper;

import cn.cloud.user.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    
    @Select("select * from tb_user where id = #{id}")
    User findById(@Param("id") Long id);

    @Insert("insert into tb_user(username,address) values(#{username},#{address})")
    void insert(User user);
    @Select("select * from tb_user where username = #{username}")
    User queryByUsername(@Param("username")String username);
}