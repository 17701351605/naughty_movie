package com.dj.movie.web.page;

import com.dj.movie.pojo.Movie;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie/")
public class MoviePageController {

    @Autowired
    private MovieService movieService;

    /**
     * 去电影详情页面
     * @author: zby
     * @throws Exception
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieDetail")
    public String toMovieDetail(Integer movieId, Model model) throws Exception {
        Movie movie = movieService.findMovieByMovieId(1);
        model.addAttribute("movie",movie);
        return "Movie/movie_comment";
    }

    /**
     * 去电影场次页面
     * @author: zby
     * @throws Exception
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieOffice")
    public String toMovieOffice() throws Exception {
        return "Movie/movie_office";
    }


    @Autowired
    private MovieService movieService;

    @RequestMapping("toMovieShow")
    private String toMovieShow(){
        return "movie/show";
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model){
        Movie movie = movieService.getById(id);
        model.addAttribute("movie",movie);
        return "movie/update";
    }
}
