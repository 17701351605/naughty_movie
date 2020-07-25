package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.MovieLike;


public interface MovieLikeService extends IService<MovieLike> {

    MovieLike findMovieLikeByUserIdAndMovieId(Integer userId, String MovieId) throws Exception;

    void updateMovieLikeScore( Integer userId,  String MovieId, Integer score) throws Exception;

    void addMovieLike (Integer userId, String MovieId, Integer score) throws Exception;

    void updateMovieLikeIsLike(Integer userId, String MovieId, Integer isLike) throws Exception;

    void addMovieLikeByUserIdAndMovieId (Integer userId, String MovieId, Integer isLike) throws Exception;
}
