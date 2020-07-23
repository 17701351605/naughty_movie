package com.dj.movie.web;

import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.service.UserOrderService;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/userorder/")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @RequestMapping("addOrder")
    public ResultModel addOrder(MovieOffice movieOffice, Integer buyNum, BigDecimal buyPrice, Integer userId){
        try{
            userOrderService.addUserOrderAndUpdateMovieSeating(movieOffice,buyNum,buyPrice,userId);
            return new ResultModel().success("购票成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("系统异常，请稍后再试");
        }
    }
}
