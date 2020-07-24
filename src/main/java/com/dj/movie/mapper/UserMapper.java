package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.User;
import org.springframework.dao.DataAccessException;

public interface UserMapper extends BaseMapper<User> {

    //用户登录
    User findNameAndPwd(User user) throws DataAccessException;

    //通过用户名或邮箱或手机号查找用户
    User findUserByName(User user) throws DataAccessException;


}
