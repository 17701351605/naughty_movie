package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieLikeMapper;
import com.dj.movie.pojo.MovieLike;
import com.dj.movie.service.MovieLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MovieLikeServiceImpl extends ServiceImpl<MovieLikeMapper, MovieLike> implements MovieLikeService {

    @Autowired
    private MovieLikeMapper movieLikeMapper;

    @Override
    public MovieLike findMovieLikeByUserIdAndMovieId(Integer userId, String MovieId) throws Exception {
        return movieLikeMapper.findMovieLikeByUserIdAndMovieId(userId, MovieId);
    }

    @Override
    public void updateMovieLikeScore(Integer userId, String MovieId, Integer score) throws Exception {
        movieLikeMapper.updateMovieLikeScore(userId, MovieId, score);
    }

    @Override
    public void addMovieLike(Integer userId, String MovieId, Integer score) throws Exception {
        movieLikeMapper.addMovieLike(userId, MovieId, score);
    }

    @Override
    public void updateMovieLikeIsLike(Integer userId, String MovieId, Integer isLike) throws Exception {
        movieLikeMapper.updateMovieLikeIsLike(userId, MovieId, isLike);
    }

    @Override
    public void addMovieLikeByUserIdAndMovieId(Integer userId, String MovieId, Integer isLike) throws Exception {
        movieLikeMapper.addMovieLikeByUserIdAndMovieId(userId, MovieId, isLike);
    }

    @Override
    public BigDecimal markGrade(Integer id) throws Exception {
        return movieLikeMapper.markGrade(id);
    }

    @Override
    public Integer isLike(Integer id) throws DataAccessException {
        return movieLikeMapper.isLike(id);
    }


}
