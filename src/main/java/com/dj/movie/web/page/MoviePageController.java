package com.dj.movie.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie/")
public class MoviePageController {

    /**
     * 去电影场次页面
     *
     * @author: zby
     * @throws Exception
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieDetail")
    public String toMovieDetail() throws Exception {
        return "Movie/movie_comment";
    }

    /**
     * 去电影场次页面
     *
     * @author: zby
     * @throws Exception
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieOffice")
    public String toMovieOffice() throws Exception {
        return "Movie/movie_office";
    }
}
