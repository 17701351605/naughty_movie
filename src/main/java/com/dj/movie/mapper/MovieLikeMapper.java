package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.MovieComment;
import com.dj.movie.pojo.MovieLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.zip.DataFormatException;


public interface MovieLikeMapper extends BaseMapper<MovieLike> {

    MovieLike findMovieLikeByUserIdAndMovieId(@Param("userId") Integer userId, @Param("MovieId") String MovieId) throws DataFormatException;

    void updateMovieLikeScore(@Param("userId") Integer userId, @Param("MovieId") String MovieId, @Param("score") Integer score) throws DataFormatException;

    void addMovieLike(@Param("userId") Integer userId, @Param("MovieId") String MovieId, @Param("score") Integer score) throws DataFormatException;

    void updateMovieLikeIsLike(@Param("userId") Integer userId, @Param("MovieId") String MovieId, @Param("isLike") Integer isLike) throws DataFormatException;

    void addMovieLikeByUserIdAndMovieId(@Param("userId") Integer userId, @Param("MovieId") String MovieId, @Param("isLike") Integer isLike) throws DataFormatException;

    Integer isLike(Integer id) throws DataAccessException;

    BigDecimal markGrade(Integer id) throws DataAccessException;
}
