package com.dj.movie.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.movie.config.UserQuery;
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

/**
 * 基础列表展示
 */

@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("show")
    public ResultModel show(Movie movie, UserQuery query){
        try{
            Map<String,Object> map = new HashMap<>();
            List<Movie> movieList = movieService.findMovieAll(query);
            map.put("pages",query.getPages());
            map.put("movieList", movieList);
            return new ResultModel().success(map);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("异常");
        }

    }





}
