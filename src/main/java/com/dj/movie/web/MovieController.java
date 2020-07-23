package com.dj.movie.web;

import com.dj.movie.mapper.MovieMapper;
import com.dj.movie.pojo.Movie;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("show")
    public ResultModel show(Movie movie){
        try{
            List<Movie> movieList = movieService.findMovieAll(movie);
            return new ResultModel().success(movieList);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("异常");
        }

    }





}
