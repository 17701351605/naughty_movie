package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dj.movie.pojo.query.MovieQuery;
import com.dj.movie.pojo.Movie;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.zip.DataFormatException;


/**
 * @author CYS
 */
public interface MovieMapper extends BaseMapper<Movie> {

    List<Movie> findMovieAll(Movie movie) throws DataAccessException;

    Movie findMovieByMovieId(String id) throws DataFormatException;

    /**
     * 自定义SQL分页
     *
     * @param page 分页信息
     * @param query 查询条件
     * @return
     */

    IPage<Movie> findMovieAll(IPage<Movie> page, @Param("query") MovieQuery query, @Param("movieName") String movieName, @Param("status") Integer status, @Param("movieType") Integer[] movieType);

    Movie findMovieById(Integer id) throws DataAccessException;
}
