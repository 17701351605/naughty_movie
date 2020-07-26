package com.dj.movie.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.movie.pojo.BaseData;
import com.dj.movie.service.BaseDataService;
import com.dj.movie.service.MovieOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userOrder/")
public class UserOrderPageController {

    @Autowired
    private MovieOfficeService movieOfficeService;

    @Autowired
    private BaseDataService baseDataService;

    @RequestMapping("toShow")
    public String toShow() {
        return "user_order/movie_show";
    }

    /**
     * 去购票
     *
     * @param id 场次ID
     * @return
     * @author fzz
     */
    @RequestMapping("toByTicket/{id}")
    public String toByTicket(@PathVariable Integer id, Model model) throws Exception {
        QueryWrapper<BaseData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 9);
        //该场次的电影信息
        model.addAttribute("movie", movieOfficeService.getById(id));
        //从数据库获取厅信息
        model.addAttribute("movieOfficeList", baseDataService.list(queryWrapper));
        return "user_order/buy_ticket";
    }

}
