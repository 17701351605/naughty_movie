package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.config.UserQuery;
import com.dj.movie.pojo.Movie;
import java.util.List;


/**
 * @author CYS
 */
public interface MovieService extends IService<Movie> {

    List<Movie> findMovieAll(Movie movie) throws Exception;

    List<Movie> findMovieAll(UserQuery query);


    Movie findMovieByMovieId(Integer id) throws Exception;

    Movie findMovieById(Integer id) throws Exception;

}
