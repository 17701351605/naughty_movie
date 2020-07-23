package com.dj.movie.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.movie.pojo.BaseData;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.service.BaseDataService;
import com.dj.movie.service.MovieOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userorder/")
public class UserOrderPageController {

    @Autowired
    private MovieOfficeService movieOfficeService;

    @Autowired
    private BaseDataService baseDataService;

    /**
     * 去购票
     * @param id 场次ID
     * @author fzz
     * @return
     */
    @RequestMapping("toByTicket")
    public String toByTicket(Integer id, Model model) throws Exception{
        QueryWrapper<BaseData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",9);
        //该场次的电影信息
        model.addAttribute("movie",movieOfficeService.getById(id));
        //从数据库获取厅信息
        model.addAttribute("movieOfficeList",baseDataService.list(queryWrapper));

        return "buy_ticket";
    }

}
