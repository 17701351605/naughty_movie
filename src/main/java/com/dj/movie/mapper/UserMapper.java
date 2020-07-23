package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.User;
import org.springframework.dao.DataAccessException;

public interface UserMapper extends BaseMapper<User> {

    User findNameAndPwd(User user) throws DataAccessException;
}
