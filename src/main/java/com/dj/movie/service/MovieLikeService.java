package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.MovieLike;
import org.apache.ibatis.annotations.Param;


public interface MovieLikeService extends IService<MovieLike> {

    MovieLike findMovieLikeByUserIdAndMovieId(Integer userId, Integer MovieId) throws Exception;

    void updateMovieLikeScore( Integer userId,  Integer MovieId, Integer score) throws Exception;

    void addMovieLike (Integer userId, Integer MovieId, Integer score) throws Exception;

    void updateMovieLikeIsLike(Integer userId, Integer MovieId, Integer isLike) throws Exception;
}
