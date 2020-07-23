package com.dj.movie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderService extends IService<UserOrder> {

    IPage<UserOrder> selectAllByUserId(IPage<UserOrder> orderPage, Integer id) throws Exception;

    void updateUserOrderByStatus(Integer status, Integer id) throws Exception;

    UserOrder findById(Integer id) throws Exception;
}
