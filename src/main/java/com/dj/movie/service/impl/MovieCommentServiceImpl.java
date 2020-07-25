package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieCommentMapper;
import com.dj.movie.pojo.MovieComment;
import com.dj.movie.service.MovieCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieCommentServiceImpl extends ServiceImpl<MovieCommentMapper, MovieComment> implements MovieCommentService {

    @Autowired
    private MovieCommentMapper movieCommentMapper;

    @Override
    public void addMovieComment(Integer userId, Integer MovieId, String remark) throws Exception {
        movieCommentMapper.addMovieComment(userId, MovieId ,remark);
    }
}
