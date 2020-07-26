package com.dj.movie.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/")
public class IndexPageController {

    @RequestMapping("toIndex")
    public String toIndex(Model model) {
        return "index/index";
    }

    @RequestMapping("toTop")
    public String toTop() {
        return "index/top";
    }

    @RequestMapping("toLeft")
    public String toLeft(Model model) {
        return "index/left";
    }

    @RequestMapping("toRight")
    public String toRight() {
        return "index/right";
    }
}
