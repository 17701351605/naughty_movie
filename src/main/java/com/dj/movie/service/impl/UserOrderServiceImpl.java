package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieOfficeMapper;
import com.dj.movie.mapper.UserOrderMapper;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private MovieOfficeMapper movieOfficeMapper;

    /**
     * 添加用户购票记录和修改电影剩余座位
     * @param movieOffice 电影场次信息
     * @param buyNum 购买数量
     * @param buyPrice 购买总价
     * @author fzz
     */
    @Override
    public void addUserOrderAndUpdateMovieSeating(MovieOffice movieOffice, Integer buyNum, BigDecimal buyPrice,
                                                  Integer userId) {
        UserOrder userOrder = new UserOrder();
        userOrder.setMovieId(movieOffice.getMovieId());
        userOrder.setMovieName(movieOffice.getMovieName());
        userOrder.setPlayHall(movieOffice.getPlayHall());
        userOrder.setPrices(buyPrice);
        userOrder.setUserId(userId);
        userOrder.setBuyNumber(buyNum);
        userOrder.setCreateTime(LocalDateTime.now());
        userOrder.setIsDel(1);
        userOrderMapper.insert(userOrder);

        UpdateWrapper<MovieOffice> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("seating",movieOffice.getSeating()-buyNum);
        updateWrapper.eq("id",movieOffice.getId());
        movieOfficeMapper.update(movieOffice,updateWrapper);

    }



}
