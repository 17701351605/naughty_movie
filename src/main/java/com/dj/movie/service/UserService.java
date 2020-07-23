package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.User;

public interface UserService extends IService<User> {

    User findNameAndPwd(User user) throws Exception;

}
