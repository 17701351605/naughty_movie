package com.dj.movie.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.service.MovieOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userorder/")
public class UserOrderPageController {

    @Autowired
    private MovieOfficeService movieOfficeService;

    /**
     * 去购票
     * @param id 场次ID
     * @author fzz
     * @return
     */
    @RequestMapping("toByTicket")
    public String toByTicket(Integer id, Model model) throws Exception{
        model.addAttribute("movie",movieOfficeService.getById(id));
        return "buy_ticket";
    }

}
