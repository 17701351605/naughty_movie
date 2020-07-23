package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.Movie;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * @author CYS
 */
public interface MovieMapper extends BaseMapper<Movie> {


    List<Movie> findMovieAll(Movie movie) throws DataAccessException;

}
