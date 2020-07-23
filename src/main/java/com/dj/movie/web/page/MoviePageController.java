package com.dj.movie.web.page;

import com.dj.movie.pojo.Movie;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie/")
public class MoviePageController {

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
