package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dj.movie.pojo.Movie;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserOrderMapper extends BaseMapper<UserOrder> {

    //查找我的订单
   IPage<UserOrder> selectAllByUserId(IPage<UserOrder> orderPage, @Param("id") Integer id) throws DataAccessException;

   void updateUserOrderByStatus(@Param("status")Integer status,@Param("id")Integer id)throws DataAccessException;

   UserOrder findById(Integer id) throws DataAccessException;

   void updateUserOrderAndUpdateMovieOffice(@Param("id")Integer id, @Param("movieOffice")MovieOffice movieOffice, @Param("userOrder")UserOrder movie) throws DataAccessException;
}
