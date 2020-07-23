package com.dj.movie.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.movie.mapper.MovieMapper;
import com.dj.movie.pojo.Movie;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.pojo.UserOrder;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("show")
    public ResultModel show(Movie movie, Integer pageNo){
        try{
            IPage<Movie> page = new Page<>(pageNo, 2);
            IPage<Movie> pageInfo = movieService.page(page);
            List<Movie> movieList = movieService.findMovieAll(movie);
            Map<String, Object> map = new HashMap<>();
            map.put("movieList", pageInfo.getRecords());
            map.put("pages", pageInfo.getPages());
            return new ResultModel().success(movieList);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("异常");
        }

    }





}
