package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.UserOrderMapper;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.UserOrderService;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

}
