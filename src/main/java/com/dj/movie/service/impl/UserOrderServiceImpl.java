package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.UserOrderMapper;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

    @Autowired
    private UserOrderMapper userOrderMapper;


    @Override
    public IPage<UserOrder> selectAllByUserId(IPage<UserOrder> orderPage, Integer id) throws Exception {
        return userOrderMapper.selectAllByUserId(orderPage,id);
    }
}
