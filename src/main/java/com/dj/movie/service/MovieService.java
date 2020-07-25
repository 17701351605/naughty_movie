package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.query.MovieQuery;
import com.dj.movie.pojo.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author CYS
 */
public interface MovieService extends IService<Movie> {

    List<Movie> findMovieAll(MovieQuery query, String movieName, Integer status, Integer[] movieType);

    Movie findMovieByMovieId(Integer id) throws Exception;

    Movie findMovieById(Integer id) throws Exception;

}
