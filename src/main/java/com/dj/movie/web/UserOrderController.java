package com.dj.movie.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.service.MovieOfficeService;
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
    private MovieOfficeService movieOfficeService;

    @Autowired
    private UserOrderService userOrderService;

    /**
     * 添加用户购买记录和减该播放厅剩余票数
     * @param movieOffice
     * @param buyNum 购买数量
     * @param buyPrice 购买总价
     * @param userId 购买人id
     * @author fzz
     */
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

    /**
     * 1.根据播放厅主键ID查出该电影此播放厅信息
     * 2.进行添加用户购买记录和减该播放厅剩余票数
     * @param id 播放厅主键ID
     * @param userId 用户ID
     * @author fzz
     */
    @RequestMapping("tuanGou")
    public ResultModel tuanGou(Integer id, Integer userId){
        try{
            MovieOffice movieOffice = movieOfficeService.getById(id);
            Integer buyNum = movieOffice.getSeating();
            BigDecimal buyPrice = movieOffice.getPrice().multiply(BigDecimal.valueOf(buyNum)).multiply(BigDecimal.valueOf(0.8));
            addOrder(movieOffice,buyNum,buyPrice,userId);
            return new ResultModel().success("购票成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("系统异常，请稍后再试");
        }
    }

}
