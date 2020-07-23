package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.Movie;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.zip.DataFormatException;


/**
 * @author CYS
 */
public interface MovieMapper extends BaseMapper<Movie> {

    List<Movie> findMovieAll(Movie movie) throws DataAccessException;

    Movie findMovieByMovieId(Integer id) throws DataFormatException;

}
