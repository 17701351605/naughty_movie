package com.dj.movie.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie/")
public class MoviePageController {



    @RequestMapping("toMovieShow")
    private String toMovieShow(){
        return "movie/show";
    }
}
