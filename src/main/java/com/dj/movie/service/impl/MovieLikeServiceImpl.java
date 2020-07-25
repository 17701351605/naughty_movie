package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieLikeMapper;
import com.dj.movie.pojo.MovieLike;
import com.dj.movie.service.MovieLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieLikeServiceImpl extends ServiceImpl<MovieLikeMapper, MovieLike> implements MovieLikeService {

    @Autowired
    private MovieLikeMapper movieLikeMapper;

    @Override
    public MovieLike findMovieLikeByUserIdAndMovieId(Integer userId, Integer MovieId) throws Exception {
        return movieLikeMapper.findMovieLikeByUserIdAndMovieId(userId, MovieId);
    }

    @Override
    public void updateMovieLikeScore(Integer userId, Integer MovieId, Integer score) throws Exception {
        movieLikeMapper.updateMovieLikeScore(userId, MovieId, score);
    }

    @Override
    public void addMovieLike(Integer userId, Integer MovieId, Integer score) throws Exception {
        movieLikeMapper.addMovieLike(userId, MovieId, score);
    }

    @Override
    public void updateMovieLikeIsLike(Integer userId, Integer MovieId, Integer isLike) throws Exception {
        movieLikeMapper.updateMovieLikeIsLike(userId, MovieId, isLike);
    }

    @Override
    public void addMovieLikeByUserIdAndMovieId(Integer userId, Integer MovieId, Integer isLike) throws Exception {
        movieLikeMapper.addMovieLikeByUserIdAndMovieId(userId, MovieId, isLike);
    }


}
