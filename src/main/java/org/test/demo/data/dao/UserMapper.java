package org.test.demo.data.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;
import org.test.demo.data.domain.User;

/**
 * Mybatis的接口类
 */
//@Component//注解配置的时候加入Component可以少看点warning
@Mapper
public interface UserMapper {

//    @Insert("INSERT INTO user(username , password) VALUE (#{username} , #{password})")
//    @Options(useGeneratedKeys = true , keyProperty = "id")
    //userGeneratedKeys和keyProperty是用于获取自增长值并返回给user的id
    int add(User user);

    User findOne(User user);
}
