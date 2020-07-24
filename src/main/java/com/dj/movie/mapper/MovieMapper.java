package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dj.movie.config.UserQuery;
import com.dj.movie.pojo.Movie;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 自定义SQL分页
     *
     * @param page 分页信息
     * @param query 查询条件
     * @return
     */

    IPage<Movie> findMovieAll(IPage<Movie> page, @Param("query") UserQuery query);

    Movie findMovieById(Integer id) throws DataAccessException;
}
