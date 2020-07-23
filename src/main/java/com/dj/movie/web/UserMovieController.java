package com.dj.movie.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.pojo.User;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.UserOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/userMovie/")
public class UserMovieController {

    @Autowired
    private UserOrderService userOrderService;

    Integer a = 1;
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
}
