package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.MovieComment;
import com.dj.movie.pojo.MovieLike;
import org.apache.ibatis.annotations.Param;

import java.util.zip.DataFormatException;


public interface MovieLikeMapper extends BaseMapper<MovieLike> {

    MovieLike findMovieLikeByUserIdAndMovieId(@Param("userId") Integer userId, @Param("MovieId") Integer MovieId) throws DataFormatException;

    void updateMovieLikeScore(@Param("userId") Integer userId, @Param("MovieId") Integer MovieId, @Param("score") Integer score) throws DataFormatException;

    void addMovieLike (@Param("userId") Integer userId, @Param("MovieId") Integer MovieId, @Param("score") Integer score) throws DataFormatException;

    void updateMovieLikeIsLike(@Param("userId") Integer userId, @Param("MovieId") Integer MovieId, @Param("isLike") Integer isLike) throws DataFormatException;
}
