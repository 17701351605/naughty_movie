package com.dj.movie.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.pojo.User;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.MovieOfficeService;
import com.dj.movie.service.UserOrderService;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userOrder/")
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
     * @param user 购买人（登录人）
     * @author fzz
     */
    @RequestMapping("addOrder")
    public ResultModel addOrder(MovieOffice movieOffice, Integer buyNum, BigDecimal buyPrice,
                                @SessionAttribute("user")User user){
        try{
            userOrderService.addUserOrderAndUpdateMovieSeating(movieOffice,buyNum,buyPrice,user.getId());
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
     * @param user 登录用户
     * @author fzz
     */
    @RequestMapping("tuanGou/{id}")
    public ResultModel tuanGou(@PathVariable Integer id, @SessionAttribute("user")User user){
        try{
            MovieOffice movieOffice = movieOfficeService.getById(id);
            Integer buyNum = movieOffice.getSeating();
            BigDecimal buyPrice = movieOffice.getPrice().multiply(BigDecimal.valueOf(buyNum)).multiply(BigDecimal.valueOf(0.8));
            addOrder(movieOffice,buyNum,buyPrice,user);
            return new ResultModel().success("购票成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("系统异常，请稍后再试");
        }
    }

    /**
     * 查询全部
     * @param a
     * @param page
     * @return
     */
    @RequestMapping("list")
    public ResultModel list(@SessionAttribute("user") User user , Integer page) {
        try {
            Map<String,Object> map = new HashMap<>();
            Page<UserOrder> orderPage = new Page<>();
            orderPage.setCurrent(page);
            IPage<UserOrder> iPage = userOrderService.selectAllByUserId(orderPage, user.getId());
            map.put("list",iPage.getRecords());
            map.put("pages",iPage.getPages());
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }

    }

    /**
     * 退票操作
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ResultModel<Object> del(Integer id) {
        try {
            //根据id先把这个票的信息查出来
            UserOrder movie = userOrderService.findById(id);
            // 拿电影的id去查出这个电影场次的座位数然后进行退票,把座位数加上
            MovieOffice movieOffice = movieOfficeService.getById(movie.getMovieId());
            //当前时间和电影开始的时间比较
            LocalDateTime localDateTime = LocalDateTime.now();
            //如果当前时间大于电影开始的时间，无法退票
            if (System.currentTimeMillis() > movieOffice.getStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
                return new ResultModel<>().error("电影已开场,您已操过退票时间,无法为您退票");
            }
            //如果当前天小于电影开始的天可以退票
            if (localDateTime.getDayOfMonth() < movieOffice.getStartTime().getDayOfMonth()) {
                userOrderService.updateUserOrderAndUpdateMovieOffice(id,movieOffice,movie);
                return new ResultModel<>().success("退票成功");
            }
            if (localDateTime.getDayOfMonth() == movieOffice.getStartTime().getDayOfMonth()) {
                if (localDateTime.getHour() > (movieOffice.getStartTime().getHour() - 3)) {
                    return new ResultModel<>().error("请在电影开始时间前三个小时退票，您已操过退票时间,无法为您退票");
                }
            }
            userOrderService.updateUserOrderAndUpdateMovieOffice(id,movieOffice,movie);
            return new ResultModel<>().success("退票成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常,请稍后再试");
        }
    }
}
