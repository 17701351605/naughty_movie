package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.User;
import com.dj.movie.pojo.UserOrder;

import java.math.BigDecimal;

public interface UserOrderService extends IService<UserOrder> {


    /**
     * 添加用户购票记录和修改电影剩余座位
     * @param movieOffice 电影场次信息
     * @param buyNum 购买数量
     * @param buyPrice 购买总价
     *  @author fzz
     */
    void addUserOrderAndUpdateMovieSeating(MovieOffice movieOffice, Integer buyNum, BigDecimal buyPrice,
                                           Integer userId);
}
