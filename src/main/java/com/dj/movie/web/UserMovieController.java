package com.dj.movie.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.pojo.User;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.MovieOfficeService;
import com.dj.movie.service.UserOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/userMovie/")
public class UserMovieController {

    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private MovieOfficeService movieOfficeService;

    /**
     * 查询全部
     * @param a
     * @param page
     * @return
     */
    @RequestMapping("list")
    public ResultModel list(/*@SessionAttribute("user") User user*/Integer a, Integer page) {
        try {
            Map<String,Object> map = new HashMap<>();
            Page<UserOrder> orderPage = new Page<>();
            orderPage.setCurrent(page);
            IPage<UserOrder> iPage = userOrderService.selectAllByUserId(orderPage, 1);
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
            MovieOffice movieOffice = movieOfficeService.getById(movie.getId());
            //当前时间和电影开始的时间比较
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(localDateTime);
            //如果当前时间大于电影开始的时间，无法退票
            if (System.currentTimeMillis() > movieOffice.getStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
                return new ResultModel<>().error("电影已开场,您已操过退票时间,无法为您退票");
            }
            //如果当前天小于电影开始的天可以退票
            if (localDateTime.getDayOfMonth() < movieOffice.getStartTime().getDayOfMonth()) {
                userOrderService.updateUserOrderByStatus(2,id);
                movieOffice.setSeating(movieOffice.getSeating() + movie.getBuyNumber());
                movieOfficeService.updateById(movieOffice);
                return new ResultModel<>().success("退票成功");
            }
            if (localDateTime.getDayOfMonth() == movieOffice.getStartTime().getDayOfMonth()) {
                if (localDateTime.getHour() > (movieOffice.getStartTime().getHour() - 3)) {
                    return new ResultModel<>().error("请在电影开始时间前三个小时退票，您已操过退票时间,无法为您退票");
                }
            }
            userOrderService.updateUserOrderByStatus(2,id);
            movieOffice.setSeating(movieOffice.getSeating() + movie.getBuyNumber());
            movieOfficeService.updateById(movieOffice);
            return new ResultModel<>().success("退票成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常,请稍后再试");
        }
    }
}
