package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dj.movie.pojo.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserOrderMapper extends BaseMapper<UserOrder> {

    //查找我的订单
   IPage<UserOrder> selectAllByUserId(IPage<UserOrder> orderPage, @Param("id") Integer id) throws DataAccessException;

}
